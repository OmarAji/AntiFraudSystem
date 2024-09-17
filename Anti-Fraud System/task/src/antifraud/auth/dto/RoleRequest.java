package antifraud.auth.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoleRequest {
    private String username;
    private String role;
}
