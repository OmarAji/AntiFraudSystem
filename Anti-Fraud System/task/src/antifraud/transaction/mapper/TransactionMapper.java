package antifraud.transaction.mapper;

import antifraud.transaction.dto.TransactionHistoryResponse;
import antifraud.transaction.dto.TransactionRequestBody;
import antifraud.transaction.entity.TransactionHistory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionMapper {

    public TransactionHistory mapTo(TransactionRequestBody transactionRequestBody) {
        return TransactionHistory.builder()
                .amount(transactionRequestBody.getAmount())
                .ip(transactionRequestBody.getIp())
                .date(transactionRequestBody.getDate())
                .number(transactionRequestBody.getNumber())
                .region(transactionRequestBody.getRegion())
                .build();

    }

    public List<TransactionHistory> MapTo(List<TransactionRequestBody> transactionRequestBodies) {
        return transactionRequestBodies.stream().map(this::mapTo).toList();
    }


    public TransactionHistoryResponse mapToTransactionHistoryResponse(TransactionHistory transactionHistory) {
        return TransactionHistoryResponse.builder()
                .transactionId(transactionHistory.getId())
                .amount(transactionHistory.getAmount())
                .ip(transactionHistory.getIp())
                .number(transactionHistory.getNumber())
                .region(transactionHistory.getRegion())
                .date(transactionHistory.getDate())
                .result(transactionHistory.getStatus().toString())
                .feedback(
                        (transactionHistory.getTransactionFeedback() != null) ?
                                transactionHistory.getTransactionFeedback().getFeedback() : ""
                )
                .build();

    }

    public List<TransactionHistoryResponse> mapToTransactionHistoryResponse(List<TransactionHistory> transactionHistories) {
        return transactionHistories.stream().map(this::mapToTransactionHistoryResponse).toList();
    }


}
