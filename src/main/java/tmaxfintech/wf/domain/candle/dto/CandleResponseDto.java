package tmaxfintech.wf.domain.candle.dto;

import lombok.Builder;

@Builder
public class CandleResponseDto {

    private Long openTime;

    private Double openPrice;

    private Double highPrice;

    private Double lowPrice;

    private Double closePrice;

    private Double volume;

    private Long closeTime;

    private String symbol;

    private String intervals;

    private CandleResponseDto() {
    }

    public CandleResponseDto(Long openTime, Double openPrice, Double highPrice, Double lowPrice, Double closePrice, Double volume, Long closeTime, String symbol, String intervals) {
        this.openTime = openTime;
        this.openPrice = openPrice;
        this.highPrice = highPrice;
        this.lowPrice = lowPrice;
        this.closePrice = closePrice;
        this.volume = volume;
        this.closeTime = closeTime;
        this.symbol = symbol;
        this.intervals = intervals;
    }

    public Long getOpenTime() {
        return openTime;
    }

    public Double getOpenPrice() {
        return openPrice;
    }

    public Double getHighPrice() {
        return highPrice;
    }

    public Double getLowPrice() {
        return lowPrice;
    }

    public Double getClosePrice() {
        return closePrice;
    }

    public Double getVolume() {
        return volume;
    }

    public Long getCloseTime() {
        return closeTime;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getIntervals() {
        return intervals;
    }
}
