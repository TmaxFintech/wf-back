package tmaxfintech.wf.domain.holdingCoin.dto;

import lombok.Builder;

@Builder
public class HoldingCoinResponseDto {

    private String symbol;
    private Double quantity;

    private Double totalPurchasePrice;

    public HoldingCoinResponseDto(String symbol, Double quantity, Double totalPurchasePrice) {
        this.symbol = symbol;
        this.quantity = quantity;
        this.totalPurchasePrice = totalPurchasePrice;
    }

    public String getSymbol() {
        return symbol;
    }

    public Double getQuantity() {
        return quantity;
    }

    public Double getTotalPurchasePrice() {
        return totalPurchasePrice;
    }
}
