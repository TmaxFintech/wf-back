package tmaxfintech.wf.domain.transactions.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tmaxfintech.wf.domain.coinAccount.entity.CoinAccount;
import tmaxfintech.wf.domain.transactions.entity.Transactions;

@Repository
public interface TransactionsRepository extends JpaRepository<Transactions, Long> {

    Page<Transactions> findAllByCoinAccount(Pageable pageable, CoinAccount coinAccount);

    Page<Transactions> findAllByCoinAccountAndSide(Pageable pageable, CoinAccount coinAccount, String side);
}
