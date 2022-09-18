package tmaxfintech.wf.domain.holdingCoin.entity;

import tmaxfintech.wf.domain.coinAccount.entity.CoinAccount;
import tmaxfintech.wf.domain.holdingCoin.dto.HoldingCoinResponseDto;

import javax.persistence.*;

@Entity
public class HoldingCoin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String symbol;

    private Double quantity;

    private Double totalPurchasePrice;

    protected HoldingCoin() {
    }

    public HoldingCoin(String symbol, Double quantity, Double totalPurchasePrice, CoinAccount coinAccount) {
        this.symbol = symbol;
        this.quantity = quantity;
        this.totalPurchasePrice = totalPurchasePrice;
        this.coinAccount = coinAccount;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coinAccount_id")
    private CoinAccount coinAccount;

    public HoldingCoinResponseDto toDto() {
        return HoldingCoinResponseDto.builder()
                .symbol(symbol)
                .quantity(quantity)
                .totalPurchasePrice(totalPurchasePrice)
                .build();
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

    public void changeQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public void changeTotalPurchasePrice(Double totalPurchasePrice) {
        this.totalPurchasePrice = totalPurchasePrice;
    }
}
