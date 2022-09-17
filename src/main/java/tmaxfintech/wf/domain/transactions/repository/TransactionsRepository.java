package tmaxfintech.wf.domain.transactions.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tmaxfintech.wf.domain.transactions.entity.Transactions;

public interface TransactionsRepository extends JpaRepository<Transactions, Long> {
}
