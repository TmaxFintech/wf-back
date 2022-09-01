package tmaxfintech.wf.domain.candle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tmaxfintech.wf.domain.candle.entity.Candle;

import java.util.List;

public interface CandleRepository extends JpaRepository<Candle, Long> {

    List<Candle> findAllBySymbolAndIntervals(String symbol, String intervals);
}
