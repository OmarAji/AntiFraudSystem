package antifraud.auth.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuthenticationResponse {
    private Long id;
    private String name;
    private String username;
    private String role;
}
