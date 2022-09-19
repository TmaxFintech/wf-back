package tmaxfintech.wf.domain.transactions.dto;

import lombok.Builder;

@Builder
public class TransactionsResponseDto {

    private String symbol;

    private Double volume;

    private Long tradingTime;

    private String side;

    private Double price;

    public TransactionsResponseDto(String symbol, Double volume, Long tradingTime, String side, Double price) {
        this.symbol = symbol;
        this.volume = volume;
        this.tradingTime = tradingTime;
        this.side = side;
        this.price = price;
    }

    public String getSymbol() {
        return symbol;
    }

    public Double getVolume() {
        return volume;
    }

    public Long getTradingTime() {
        return tradingTime;
    }

    public String getSide() {
        return side;
    }

    public Double getPrice() {
        return price;
    }
}
