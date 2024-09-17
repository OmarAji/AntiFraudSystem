package antifraud.transaction.repository;

import antifraud.transaction.entity.TransactionFeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionFeedbackRepository extends JpaRepository<TransactionFeedback, Long> {
    Optional<TransactionFeedback> findByTransactionHistoryId(Long transactionHistoryId);
}
