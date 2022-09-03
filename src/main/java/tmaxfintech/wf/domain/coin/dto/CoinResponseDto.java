package tmaxfintech.wf.domain.coin.dto;

import lombok.Builder;

@Builder
public class CoinResponseDto {

    private String symbol;

    private Double priceChange;

    private Double priceChangePercent;

    private Double weightedAvgPrice;

    private Double openPrice;

    private Double highPrice;

    private Double lowPrice;

    private Double lastPrice;

    private Double volume;

    public CoinResponseDto(String symbol, Double priceChange, Double priceChangePercent, Double weightedAvgPrice, Double openPrice, Double highPrice, Double lowPrice, Double lastPrice, Double volume) {
        this.symbol = symbol;
        this.priceChange = priceChange;
        this.priceChangePercent = priceChangePercent;
        this.weightedAvgPrice = weightedAvgPrice;
        this.openPrice = openPrice;
        this.highPrice = highPrice;
        this.lowPrice = lowPrice;
        this.lastPrice = lastPrice;
        this.volume = volume;
    }

    public String getSymbol() {
        return symbol;
    }

    public Double getPriceChange() {
        return priceChange;
    }

    public Double getPriceChangePercent() {
        return priceChangePercent;
    }

    public Double getWeightedAvgPrice() {
        return weightedAvgPrice;
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

    public Double getLastPrice() {
        return lastPrice;
    }

    public Double getVolume() {
        return volume;
    }
}
