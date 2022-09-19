package tmaxfintech.wf.domain.coinAccount.entity;

import tmaxfintech.wf.domain.coinAccount.dto.CoinAccountResponseDto;
import tmaxfintech.wf.domain.holdingCoin.entity.HoldingCoin;
import tmaxfintech.wf.domain.user.entity.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class CoinAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double cash;

    protected CoinAccount() {
    }

    public CoinAccount(Double cash) {
        this.cash = cash;
    }

    @OneToOne(mappedBy = "coinAccount")
    private User user;

    @OneToMany(mappedBy = "coinAccount")
    private List<HoldingCoin> holdingCoins = new ArrayList<>();

    public CoinAccountResponseDto toDto() {
        return CoinAccountResponseDto.builder()
                .cash(cash)
                .holdingCoinResponseDtos(holdingCoins.stream().map(HoldingCoin::toDto).collect(Collectors.toList()))
                .build();
    }

    public Double getCash() {
        return cash;
    }

    public List<HoldingCoin> getHoldingCoins() {
        return holdingCoins;
    }

    public void changeCash(Double cash) {
        this.cash = cash;
    }
}
