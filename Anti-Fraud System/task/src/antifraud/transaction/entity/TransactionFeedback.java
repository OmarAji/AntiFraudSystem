package antifraud.transaction.entity;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "transaction_feedback")
public class TransactionFeedback {

    @Id
    @GeneratedValue
    private long id;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "transactionFeedback", optional = false)
    @JoinColumn(name = "transaction_history_id")
    private TransactionHistory transactionHistory;

    private String feedback;

    @Override
    public String toString() {
        return "TransactionFeedback{" +
                "id=" + id +
                ", transactionHistory=" + ((transactionHistory== null)? null : transactionHistory.getId()) +
                ", feedback='" + feedback + '\'' +
                '}';
    }
}
