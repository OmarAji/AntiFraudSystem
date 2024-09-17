package antifraud.card.entity;


import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "stolencard")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class StolenCard {

    @GeneratedValue
    @Id
    private Long id;

    @Column(name = "ip")
    private String number;
}


