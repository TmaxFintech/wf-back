package tmaxfintech.wf.domain.predictedPrice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tmaxfintech.wf.domain.predictedPrice.entity.PredictedPrice;

import java.util.Optional;

public interface PredictedPriceRepository extends JpaRepository<PredictedPrice, Long> {
    Optional<PredictedPrice> findBySymbolAndIntervals(String symbol, String intervals);
}
