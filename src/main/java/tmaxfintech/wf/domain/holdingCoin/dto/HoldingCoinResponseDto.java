package tmaxfintech.wf.domain.holdingCoin.dto;

import lombok.Builder;
import tmaxfintech.wf.domain.coin.dto.CoinResponseDto;

@Builder
public class HoldingCoinResponseDto {

    private CoinResponseDto coinResponseDto;

    private Double quantity;

    private Double totalPurchasePrice;

    public HoldingCoinResponseDto(CoinResponseDto coinResponseDto, Double quantity, Double totalPurchasePrice) {
        this.coinResponseDto = coinResponseDto;
        this.quantity = quantity;
        this.totalPurchasePrice = totalPurchasePrice;
    }

    public CoinResponseDto getCoinResponseDto() {
        return coinResponseDto;
    }

    public Double getQuantity() {
        return quantity;
    }

    public Double getTotalPurchasePrice() {
        return totalPurchasePrice;
    }
}
