package tmaxfintech.wf.domain.coin.entity;

import lombok.Builder;
import org.springframework.beans.factory.annotation.Value;
import tmaxfintech.wf.domain.coin.dto.CoinResponseDto;

import javax.persistence.*;

@Builder
@Entity
public class Coin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String symbol;

    private Double priceChange;

    private Double priceChangePercent;

    private Double weightedAvgPrice;

    private Double openPrice;

    private Double highPrice;

    private Double lowPrice;

    private Double lastPrice;

    private Double volume;

    protected Coin() {
    }

    public Coin(Long id, String symbol, Double priceChange, Double priceChangePercent, Double weightedAvgPrice, Double openPrice, Double highPrice, Double lowPrice, Double lastPrice, Double volume) {
        this.id = id;
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

    public void updateCoin(Coin coin) {
        this.symbol = coin.getSymbol();
        this.priceChange = coin.getPriceChange();
        this.priceChangePercent = coin.getPriceChangePercent();
        this.weightedAvgPrice = coin.getWeightedAvgPrice();
        this.openPrice = coin.getOpenPrice();
        this.highPrice = coin.getHighPrice();
        this.lowPrice = coin.getLowPrice();
        this.lastPrice = coin.getLastPrice();
        this.volume = coin.getVolume();
    }

    public CoinResponseDto toDto() {
        return CoinResponseDto.builder()
                .symbol(symbol)
                .priceChange(priceChange)
                .priceChangePercent(priceChangePercent)
                .weightedAvgPrice(weightedAvgPrice)
                .openPrice(openPrice)
                .highPrice(highPrice)
                .lowPrice(lowPrice)
                .lastPrice(lastPrice)
                .volume(volume)
                .build();
    }
}
