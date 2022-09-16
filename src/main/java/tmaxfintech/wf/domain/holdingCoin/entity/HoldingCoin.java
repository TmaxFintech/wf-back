package tmaxfintech.wf.domain.holdingCoin.entity;

import tmaxfintech.wf.domain.coin.entity.Coin;
import tmaxfintech.wf.domain.coinAccount.entity.CoinAccount;
import tmaxfintech.wf.domain.holdingCoin.dto.HoldingCoinResponseDto;

import javax.persistence.*;

@Entity
public class HoldingCoin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private Coin coin;

    private Double quantity;

    private Double totalPurchasePrice;

    protected HoldingCoin() {
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coinAccount_id")
    private CoinAccount coinAccount;

    public HoldingCoinResponseDto toDto() {
        return HoldingCoinResponseDto.builder()
                .coinResponseDto(coin.toDto())
                .quantity(quantity)
                .totalPurchasePrice(totalPurchasePrice)
                .build();
    }
}
