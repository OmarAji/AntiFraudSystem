package antifraud.transaction.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "transaction")
@ToString
public class TransactionHistory {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "amount")
    private Long amount;
    @Column(name = "ip")
    private String ip;
    @Column(name = "number")
    private String number;
    @Column(name = "region")
    private String region;
    @Column(name = "date")
    private LocalDateTime date;
    @Column(name = "transactionStatus")
    private TransactionStatus status;

    @OneToOne(fetch = FetchType.EAGER)
    private TransactionFeedback transactionFeedback;
}
