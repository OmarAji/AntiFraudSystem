package antifraud.ip.dto;


import antifraud.ip.validation.ValidIPv4;
import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class SuspiciousIpRequest {

    @ValidIPv4(message = "The IP address provided is not a valid IPv4 address ")
    private String ip;
}
