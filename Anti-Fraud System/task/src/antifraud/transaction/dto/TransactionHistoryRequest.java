package antifraud.transaction.dto;


import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class TransactionHistoryRequest {
    @NotNull
    private Long transactionId;
    private String feedback;
}
