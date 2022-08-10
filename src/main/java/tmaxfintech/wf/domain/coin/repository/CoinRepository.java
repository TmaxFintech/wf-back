package tmaxfintech.wf.domain.coin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tmaxfintech.wf.domain.coin.entity.Coin;

import java.util.Optional;

public interface CoinRepository extends JpaRepository<Coin, Long> {

    Optional<Coin> findBySymbol(String symbol);
}
