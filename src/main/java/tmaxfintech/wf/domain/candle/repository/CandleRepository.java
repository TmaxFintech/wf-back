package tmaxfintech.wf.domain.candle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tmaxfintech.wf.domain.candle.entity.Candle;

public interface CandleRepository extends JpaRepository<Candle, Long> {

}
