package antifraud.transaction;


import antifraud.card.StolenCardRepository;
import antifraud.ip.SuspiciousIpRepository;
import antifraud.transaction.dto.TransactionHistoryRequest;
import antifraud.transaction.dto.TransactionHistoryResponse;
import antifraud.transaction.dto.TransactionRequestBody;
import antifraud.transaction.dto.TransactionResponse;
import antifraud.transaction.entity.TransactionConfig;
import antifraud.transaction.entity.TransactionFeedback;
import antifraud.transaction.entity.TransactionHistory;
import antifraud.transaction.entity.TransactionStatus;
import antifraud.transaction.exception.FeedbackFormatException;
import antifraud.transaction.exception.TransactionAlreadyExistException;
import antifraud.transaction.exception.TransactionNotFoundException;
import antifraud.transaction.exception.UnprocessableException;
import antifraud.transaction.mapper.TransactionMapper;
import antifraud.transaction.repository.TransactionConfigRepository;
import antifraud.transaction.repository.TransactionFeedbackRepository;
import antifraud.transaction.repository.TransactionHistoryRepository;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionMapper transactionMapper;
    private final TransactionHistoryRepository transactionHistoryRepository;
    private final TransactionFeedbackRepository transactionFeedbackRepository;

    private final StolenCardRepository stolenCardRepository;
    private final SuspiciousIpRepository suspiciousIpRepository;
    private final TransactionConfigRepository transactionConfigRepository;

    private TransactionConfig transactionConfig;

    @PostConstruct
    private void init() {
        loadTransactionConfig();
    }

    private void loadTransactionConfig() {
        transactionConfig = transactionConfigRepository.findById(1L).orElseGet(() -> {
                    return TransactionConfig.builder()
                            .maxAllowed(200)
                            .maxManual(1500)
                            .id(1L)
                            .build();
                }
        );
    }

    public TransactionResponse checkTransaction(TransactionRequestBody requestBody) {
        var transaction = transactionMapper.mapTo(requestBody);
        var result = TransactionResponse.builder()
                .info("")
                .build();

        if (transaction.getAmount() <= transactionConfig.getMaxAllowed()) {
            result.setResult(TransactionStatus.ALLOWED);
            result.setInfo("none, ");
        }
        if (transaction.getAmount() <= transactionConfig.getMaxManual() && transaction.getAmount() > transactionConfig.getMaxAllowed()) {
            result.setResult(TransactionStatus.MANUAL_PROCESSING);
            result.setInfo("amount, ");
        }
        if (transaction.getAmount() > transactionConfig.getMaxManual()) {
            result.setResult(TransactionStatus.PROHIBITED);
            result.setInfo("amount, ");
        }
        if (stolenCardRepository.findByNumber(requestBody.getNumber()).isPresent()) {
            result.setInfo((result.getInfo().contains("none") || !result.getResult().equals(TransactionStatus.PROHIBITED)) ? "card-number, " : result.getInfo() + "card-number, ");
            result.setResult(TransactionStatus.PROHIBITED);
        }
        if (suspiciousIpRepository.findByIp(requestBody.getIp()).isPresent()) {
            result.setInfo((result.getInfo().contains("none") || !result.getResult().equals(TransactionStatus.PROHIBITED)) ? "ip, " : result.getInfo() + "ip, ");
            result.setResult(TransactionStatus.PROHIBITED);
        }

        LocalDateTime relatedTime = transaction.getDate().minusHours(1);

        List<String> transactionsDifferentIps = transactionHistoryRepository.findTransactionsFromDifferentIPs(
                transaction.getNumber(), relatedTime, transaction.getDate(), transaction.getIp());
        if (transactionsDifferentIps.size() > 2) {
            result.setInfo(result.getInfo().contains("none") ? "ip-correlation, " : result.getInfo() + "ip-correlation, ");
            result.setResult(TransactionStatus.PROHIBITED);
        }
        if (transactionsDifferentIps.size() == 2) {
            result.setInfo((result.getInfo().contains("none") || !result.getResult().equals(TransactionStatus.PROHIBITED)) ? "ip-correlation, " : result.getInfo() + "ip-correlation, ");
            result.setResult(TransactionStatus.MANUAL_PROCESSING);
        }


        List<String> transactionsDifferentRegions = transactionHistoryRepository.findTransactionsFromDifferentRegions(
                transaction.getNumber(), relatedTime, transaction.getDate(), transaction.getRegion());

        if (transactionsDifferentRegions.size() > 2) {
            result.setInfo(result.getInfo().contains("none") ? "region-correlation, " : result.getInfo() + "region-correlation, ");
            result.setResult(TransactionStatus.PROHIBITED);
        }
        if (transactionsDifferentRegions.size() == 2) {
            result.setInfo((result.getInfo().contains("none") || !result.getResult().equals(TransactionStatus.PROHIBITED)) ? "region-correlation, " : result.getInfo() + "region-correlation, ");
            result.setResult(TransactionStatus.MANUAL_PROCESSING);
        }


        result.setInfo(result.getInfo().substring(0, result.getInfo().length() - 2));
        transaction.setStatus(result.getResult());
        transactionHistoryRepository.save(transaction);

        return result;
    }


    public TransactionHistoryResponse addFeedbackToTransaction(TransactionHistoryRequest transactionHistoryRequest) {
        var transaction = transactionHistoryRepository.findById(transactionHistoryRequest.getTransactionId());
        if (transaction.isEmpty()) {
            throw new TransactionNotFoundException("transaction not found");
        }
        var transactionFeedback = transactionFeedbackRepository.findByTransactionHistoryId(transaction.get().getId());
        if (!TransactionStatus.isValidEnumValue(transactionHistoryRequest.getFeedback())) {
            throw new FeedbackFormatException("feedback wrong formated");
        }
        if (transaction.get().getTransactionFeedback() != null) {
            throw new TransactionAlreadyExistException("feedback already Exist");
        }
        if (transaction.get().getStatus().toString().equals(transactionHistoryRequest.getFeedback())) {
            throw new UnprocessableException("change can not saved");
        }

        updateMaxAllowedValue(transactionHistoryRequest, transaction.get());
        updateMaxManualValue(transactionHistoryRequest, transaction.get());
        transactionConfigRepository.save(transactionConfig);

        var newFeedback = TransactionFeedback.builder()
                .feedback(transactionHistoryRequest.getFeedback())
                .transactionHistory(transaction.get())
                .build();
        transaction.get().setTransactionFeedback(newFeedback);
        transactionFeedbackRepository.save(newFeedback);
        transactionHistoryRepository.save(transaction.get());
        return transactionMapper.mapToTransactionHistoryResponse(transaction.get());
    }


    public List<TransactionHistoryResponse> getTransactionHistory() {
        var transactions = transactionHistoryRepository.findAll();
        return transactionMapper.mapToTransactionHistoryResponse(transactions);
    }

    public List<TransactionHistoryResponse> getTransactionHistoryByNumber(@Valid String number) {
        var transactions = transactionHistoryRepository.findByNumber(number);
        if (transactions.isEmpty()) {
            throw new TransactionNotFoundException("transaction not found");
        }
        return transactionMapper.mapToTransactionHistoryResponse(transactions);
    }

    private void updateMaxManualValue(TransactionHistoryRequest transactionHistoryRequest, TransactionHistory transaction) {
        if (
                (TransactionStatus.ALLOWED.isEqual(transactionHistoryRequest.getFeedback())
                        || TransactionStatus.MANUAL_PROCESSING.isEqual(transactionHistoryRequest.getFeedback())
                )
                        && TransactionStatus.PROHIBITED.equals(transaction.getStatus())
        ) {
            transactionConfig.setMaxManual(Math.ceil(0.8 * transactionConfig.getMaxManual() + 0.2 * transaction.getAmount()));

        }
        if (
                (TransactionStatus.ALLOWED.equals(transaction.getStatus())
                        || TransactionStatus.MANUAL_PROCESSING.equals(transaction.getStatus())
                )
                        && TransactionStatus.PROHIBITED.isEqual(transactionHistoryRequest.getFeedback())
        ) {
            transactionConfig.setMaxManual(Math.ceil(0.8 * transactionConfig.getMaxManual() - 0.2 * transaction.getAmount()));
        }

    }


    private void updateMaxAllowedValue(TransactionHistoryRequest transactionHistoryRequest, TransactionHistory transaction) {
        if (
                (TransactionStatus.PROHIBITED.equals(transaction.getStatus())
                        || TransactionStatus.MANUAL_PROCESSING.equals(transaction.getStatus())
                )
                        && TransactionStatus.ALLOWED.isEqual(transactionHistoryRequest.getFeedback())


        ) {
            transactionConfig.setMaxAllowed(Math.ceil(0.8 * transactionConfig.getMaxAllowed() + 0.2 * transaction.getAmount()));
        }
        if (
                (TransactionStatus.PROHIBITED.isEqual(transactionHistoryRequest.getFeedback())
                        || TransactionStatus.MANUAL_PROCESSING.isEqual(transactionHistoryRequest.getFeedback())
                )
                        && TransactionStatus.ALLOWED.equals(transaction.getStatus())
        ) {
            transactionConfig.setMaxAllowed(Math.ceil(0.8 * transactionConfig.getMaxAllowed() - 0.2 * transaction.getAmount()));
        }
    }
}



