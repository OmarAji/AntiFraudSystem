package antifraud.auth.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DeletionResponse {
    private String username;
    private final String status = "Deleted successfully!";
}
