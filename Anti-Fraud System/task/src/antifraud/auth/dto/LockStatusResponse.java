package antifraud.auth.dto;


import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LockStatusResponse {
    private String status;
}
