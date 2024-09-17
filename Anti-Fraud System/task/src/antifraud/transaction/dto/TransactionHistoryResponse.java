package antifraud.transaction.dto;


import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class TransactionHistoryResponse {

    private Long transactionId;
    private Long amount;
    private String ip;
    private String number;
    private String region;
    private LocalDateTime date;
    private String result;
    private String feedback;

}