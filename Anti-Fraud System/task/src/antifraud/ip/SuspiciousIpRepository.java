package antifraud.ip;

import antifraud.ip.Entity.SuspiciousIp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface SuspiciousIpRepository extends JpaRepository<SuspiciousIp,Long> {

    Optional<SuspiciousIp> findByIp(String ip);
}
