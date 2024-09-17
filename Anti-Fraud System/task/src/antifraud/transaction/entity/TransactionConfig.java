package antifraud.transaction.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Transaction_config")
public class TransactionConfig {

    @Id
    private Long id = 1L;

    private double maxAllowed;
    private double maxManual;
}
