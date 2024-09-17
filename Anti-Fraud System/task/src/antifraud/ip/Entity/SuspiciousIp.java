package antifraud.ip.Entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "bandedIp")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class SuspiciousIp {

    @GeneratedValue
    @Id
    private Long id;

    @Column(name = "ip")
    private String ip;

}

