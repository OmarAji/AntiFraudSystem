package antifraud.transaction;

import antifraud.card.validation.ValidCardNumber;
import antifraud.transaction.dto.TransactionHistoryRequest;
import antifraud.transaction.dto.TransactionHistoryResponse;
import antifraud.transaction.dto.TransactionRequestBody;
import antifraud.transaction.dto.TransactionResponse;
import antifraud.utility.CardUtility;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/antifraud")
@RequiredArgsConstructor
@Validated
public class TransactionController {

    private final TransactionService transactionService;


    @PostMapping("/transaction")
    public ResponseEntity<TransactionResponse> checkTransaction(@RequestBody @Valid TransactionRequestBody requestBody) {
        System.out.println(requestBody);
        return ResponseEntity.ok(transactionService.checkTransaction(requestBody));
    }

    @PutMapping("/transaction")
    public ResponseEntity<TransactionHistoryResponse> addFeedbackToTransaction(
            @RequestBody @Valid TransactionHistoryRequest transactionHistoryRequest
    ) {
        return ResponseEntity.ok(transactionService.addFeedbackToTransaction(transactionHistoryRequest));
    }


    @GetMapping("/history")
    public ResponseEntity<List<TransactionHistoryResponse>> getTransactionHistory() {
        return ResponseEntity.ok(transactionService.getTransactionHistory());
    }



    @GetMapping("/history/{number}")
    public ResponseEntity<List<TransactionHistoryResponse>> getTransactionHistory(@Valid @PathVariable @ValidCardNumber String number) {
        return ResponseEntity.ok(transactionService.getTransactionHistoryByNumber(number));
    }

}
