package antifraud.card.dto;


import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class StolenCardResponse {

    private String number;
    private Long id;
}
