package antifraud.card.dto;


import antifraud.card.validation.ValidCardNumber;
import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class RegisterStolenCardRequest {
    @ValidCardNumber(message = "the Card number is not correct")
    private String number;

}
