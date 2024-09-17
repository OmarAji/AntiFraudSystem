package antifraud.transaction.repository;

import antifraud.transaction.entity.TransactionConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TransactionConfigRepository extends JpaRepository<TransactionConfig, Long> {
}
