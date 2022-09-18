package tmaxfintech.wf.domain.holdingCoin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tmaxfintech.wf.domain.holdingCoin.entity.HoldingCoin;

public interface HoldingCoinRepository extends JpaRepository<HoldingCoin, Long> {
}
