package antifraud.ip.dto;


import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SuspiciousIpResponse {

    private String ip;
    private Long id;
}
