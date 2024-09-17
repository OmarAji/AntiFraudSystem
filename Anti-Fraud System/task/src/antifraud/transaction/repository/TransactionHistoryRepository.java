package antifraud.transaction.repository;

import antifraud.transaction.entity.TransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, Long> {


    @Query("SELECT DISTINCT t.region FROM TransactionHistory t WHERE t.number = :number AND t.date > :start AND t.date < :end AND t.region <> :region")
    List<String> findTransactionsFromDifferentRegions(@Param("number") String number, @Param("start") LocalDateTime start, @Param("end") LocalDateTime end, @Param("region") String region);


    @Query("SELECT DISTINCT t.ip FROM TransactionHistory t WHERE t.number = :number AND t.date > :start AND t.date < :end AND t.ip <> :ip")
    List<String> findTransactionsFromDifferentIPs(@Param("number") String number, @Param("start") LocalDateTime start, @Param("end") LocalDateTime end, @Param("ip") String ip);


    List<TransactionHistory> findByNumber(String number);

}
