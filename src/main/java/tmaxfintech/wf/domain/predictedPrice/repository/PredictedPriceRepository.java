package tmaxfintech.wf.domain.predictedPrice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tmaxfintech.wf.domain.predictedPrice.entity.PredictedPrice;

import java.util.List;
import java.util.Optional;

public interface PredictedPriceRepository extends JpaRepository<PredictedPrice, Long> {
    Optional<PredictedPrice> findBySymbolAndIntervals(String symbol, String intervals);

    List<PredictedPrice> findByIntervals(String intervals);
}
