package tmaxfintech.wf.domain.coinAccount.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tmaxfintech.wf.domain.coinAccount.entity.CoinAccount;

import java.util.Optional;

public interface CoinAccountRepository extends JpaRepository<CoinAccount, Long> {

    @Query(
            value = "select c from CoinAccount c join fetch c.user where c.user.username = :username"
    )
    Optional<CoinAccount> findWithUsername(@Param("username") String username);
}
