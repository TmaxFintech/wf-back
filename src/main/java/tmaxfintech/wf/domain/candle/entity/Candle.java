package tmaxfintech.wf.domain.candle.entity;

import lombok.Builder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Builder
@Entity
public class Candle {

    @Id
    @GeneratedValue
    private Long id;

    private Double openTime;

    private Double open;

    private Double high;

    private Double low;

    private Double close;

    private Double volume;

    private Long closeTime;

    private Double quoteAssetVolume;

    private Long numberOfTrades;

    private Double takerBuyBaseAssetVolume;

    private Double takerBuyQuoteAssetVolume;

    private Double ignores;

    public Candle() {
    }

    public Candle(Long id, Double openTime, Double open, Double high, Double low, Double close, Double volume, Long closeTime, Double quoteAssetVolume, Long numberOfTrades, Double takerBuyBaseAssetVolume, Double takerBuyQuoteAssetVolume, Double ignores) {
        this.id = id;
        this.openTime = openTime;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.volume = volume;
        this.closeTime = closeTime;
        this.quoteAssetVolume = quoteAssetVolume;
        this.numberOfTrades = numberOfTrades;
        this.takerBuyBaseAssetVolume = takerBuyBaseAssetVolume;
        this.takerBuyQuoteAssetVolume = takerBuyQuoteAssetVolume;
        this.ignores = ignores;
    }

    public Double getOpenTime() {
        return openTime;
    }

    public Double getOpen() {
        return open;
    }

    public Double getHigh() {
        return high;
    }

    public Double getLow() {
        return low;
    }

    public Double getClose() {
        return close;
    }

    public Double getVolume() {
        return volume;
    }

    public Long getCloseTime() {
        return closeTime;
    }

    public Double getQuoteAssetVolume() {
        return quoteAssetVolume;
    }

    public Long getNumberOfTrades() {
        return numberOfTrades;
    }

    public Double getTakerBuyBaseAssetVolume() {
        return takerBuyBaseAssetVolume;
    }

    public Double getTakerBuyQuoteAssetVolume() {
        return takerBuyQuoteAssetVolume;
    }

    public Double getIgnores() {
        return ignores;
    }
}
