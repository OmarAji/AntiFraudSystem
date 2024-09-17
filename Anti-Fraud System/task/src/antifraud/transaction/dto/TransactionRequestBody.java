package antifraud.transaction.dto;


import antifraud.card.validation.ValidCardNumber;
import antifraud.ip.validation.ValidIPv4;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class TransactionRequestBody {
    @Positive
    @NotNull
    private Long amount;
    @ValidIPv4
    private String ip;
    @ValidCardNumber
    private String number;

    @Pattern(regexp = "EAP|ECA|HIC|LAC|MENA|SA|SSA", message = "must be one of the values: EAP,ECA,HIC,LAC,MENA,SA,SSA")
    private String region;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime date;

}
