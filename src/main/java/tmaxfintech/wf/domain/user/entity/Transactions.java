package tmaxfintech.wf.domain.user.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coinAccount_id")
    private CoinAccount coinAccount;

    private String symbol;
    private Double quantity;

    private TransactionType transactionType;

    private Timestamp times;
    private Double price;

    public Transactions(Long id, String symbol, Double quantity, TransactionType transactionType, Timestamp times, Double price) {
        this.id = id;
        this.symbol = symbol;
        this.quantity = quantity;
        this.transactionType = transactionType;
        this.times = times;
        this.price = price;
    }
}