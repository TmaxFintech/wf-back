package tmaxfintech.wf.domain.coin.dto;

import tmaxfintech.wf.domain.coin.entity.Coin;

public class CoinFeignDto {
    private String symbol;

    private Double priceChange;

    private Double priceChangePercent;

    private Double weightedAvgPrice;

    private Double openPrice;

    private Double highPrice;

    private Double lowPrice;

    private Double lastPrice;

    private Double volume;

    private Double quoteVolume;

    private Long openTime;

    private Long closeTime;

    private Long firstId;

    private Long lastId;

    private Long count;

    protected CoinFeignDto() {
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

    public Double getQuoteVolume() {
        return quoteVolume;
    }

    public Long getOpenTime() {
        return openTime;
    }

    public Long getCloseTime() {
        return closeTime;
    }

    public Long getFirstId() {
        return firstId;
    }

    public Long getLastId() {
        return lastId;
    }

    public Long getCount() {
        return count;
    }
}
