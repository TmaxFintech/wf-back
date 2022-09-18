package tmaxfintech.wf.domain.transactions.entity;

import tmaxfintech.wf.domain.coinAccount.entity.CoinAccount;
import tmaxfintech.wf.domain.transactions.dto.TransactionsResponseDto;

import javax.persistence.*;

@Entity
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String symbol;

    private Double volume;

    private Long tradingTime;

    private String side;

    private Double price;

    @ManyToOne(fetch = FetchType.LAZY)
    private CoinAccount coinAccount;

    protected Transactions() {
    }

    public Transactions(String symbol, Double volume, Long tradingTime, String side, CoinAccount coinAccount, Double price) {
        this.symbol = symbol;
        this.volume = volume;
        this.tradingTime = tradingTime;
        this.side = side;
        this.coinAccount = coinAccount;
        this.price = price;
    }

    public TransactionsResponseDto toDto() {
        return TransactionsResponseDto.builder()
            .symbol(symbol)
            .volume(volume)
            .tradingTime(tradingTime)
            .side(side)
            .price(price)
            .build();
    }
}
