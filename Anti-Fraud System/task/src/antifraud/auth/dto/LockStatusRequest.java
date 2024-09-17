package antifraud.auth.dto;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LockStatusRequest {
    private String username;
    private String operation;
}
