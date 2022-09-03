package tmaxfintech.wf.domain.candle.entity;

import lombok.Builder;
import lombok.ToString;
import tmaxfintech.wf.domain.candle.dto.CandleResponseDto;

import javax.persistence.*;

@Builder
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"openTime", "intervals", "symbol"})})
public class Candle {

    @Id
    @GeneratedValue
    private Long id;

    private Long openTime;

    private Double openPrice;

    private Double highPrice;

    private Double lowPrice;

    private Double closePrice;

    private Double volume;

    private Long closeTime;

    private Double quoteAssetVolume;

    private Long numberOfTrades;

    private Double takerBuyBaseAssetVolume;

    private Double takerBuyQuoteAssetVolume;

    private Double ignores;

    private String symbol;

    private String intervals;

    protected Candle() {
    }

    public Candle(Long id, Long openTime, Double openPrice, Double highPrice, Double lowPrice, Double closePrice, Double volume, Long closeTime, Double quoteAssetVolume, Long numberOfTrades, Double takerBuyBaseAssetVolume, Double takerBuyQuoteAssetVolume, Double ignores, String symbol, String intervals) {
        this.id = id;
        this.openTime = openTime;
        this.openPrice = openPrice;
        this.highPrice = highPrice;
        this.lowPrice = lowPrice;
        this.closePrice = closePrice;
        this.volume = volume;
        this.closeTime = closeTime;
        this.quoteAssetVolume = quoteAssetVolume;
        this.numberOfTrades = numberOfTrades;
        this.takerBuyBaseAssetVolume = takerBuyBaseAssetVolume;
        this.takerBuyQuoteAssetVolume = takerBuyQuoteAssetVolume;
        this.ignores = ignores;
        this.symbol = symbol;
        this.intervals = intervals;
    }

    public CandleResponseDto toDto() {
        return CandleResponseDto.builder()
                .openTime(openTime)
                .openPrice(openPrice)
                .highPrice(highPrice)
                .lowPrice(lowPrice)
                .closePrice(closePrice)
                .volume(volume)
                .closeTime(closeTime)
                .symbol(symbol)
                .intervals(intervals)
                .build();
    }
}
