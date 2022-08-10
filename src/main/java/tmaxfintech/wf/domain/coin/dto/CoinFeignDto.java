package tmaxfintech.wf.domain.coin.dto;

import tmaxfintech.wf.domain.coin.entity.Coin;

public class CoinFeignDto {
    private String symbol;

    private Double price;

    public String getSymbol() {
        return symbol;
    }

    public Double getPrice() {
        return price;
    }

    public Coin toEntity() {
        return Coin.builder()
                .symbol(symbol)
                .price(price)
                .build();
    }
}
