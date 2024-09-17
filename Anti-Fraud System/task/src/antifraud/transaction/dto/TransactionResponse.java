package antifraud.transaction.dto;


import antifraud.transaction.entity.TransactionStatus;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionResponse {
    private TransactionStatus result;
    private String info;
}
