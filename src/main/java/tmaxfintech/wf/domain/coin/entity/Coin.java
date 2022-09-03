package tmaxfintech.wf.domain.coin.entity;

import lombok.Builder;
import org.springframework.beans.factory.annotation.Value;
import tmaxfintech.wf.domain.coin.dto.CoinFeignDto;
import tmaxfintech.wf.domain.coin.dto.CoinResponseDto;

import javax.persistence.*;

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

    public void updateCoin(CoinFeignDto coinFeignDto) {
        this.symbol = coinFeignDto.getSymbol();
        this.priceChange = coinFeignDto.getPriceChange();
        this.priceChangePercent = coinFeignDto.getPriceChangePercent();
        this.weightedAvgPrice = coinFeignDto.getWeightedAvgPrice();
        this.openPrice = coinFeignDto.getOpenPrice();
        this.highPrice = coinFeignDto.getHighPrice();
        this.lowPrice = coinFeignDto.getLowPrice();
        this.lastPrice = coinFeignDto.getLastPrice();
        this.volume = coinFeignDto.getVolume();
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
