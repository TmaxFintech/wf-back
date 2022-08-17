package tmaxfintech.wf.domain.user.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class CoinAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double btcusdtQuantitiy;
    private Double ethusdtQuantitiy;
    private Double xrpusdtQuantitiy;
    private Double adausdtQuantitiy;

    private Double btcusdtPurchaseAmount;
    private Double ethusdtPurchaseAmount;
    private Double xrpusdtPurchaseAmount;
    private Double adausdtPurchaseAmount;

    @OneToMany(mappedBy = "coinAccount")
    private List<Transactions> transactions;

    public CoinAccount(Long id, Double btcusdtQuantitiy, Double ethusdtQuantitiy, Double xrpusdtQuantitiy, Double adausdtQuantitiy, Double btcusdtPurchaseAmount, Double ethusdtPurchaseAmount, Double xrpusdtPurchaseAmount, Double adausdtPurchaseAmount, List<Transactions> transactions) {
        this.id = id;
        this.btcusdtQuantitiy = btcusdtQuantitiy;
        this.ethusdtQuantitiy = ethusdtQuantitiy;
        this.xrpusdtQuantitiy = xrpusdtQuantitiy;
        this.adausdtQuantitiy = adausdtQuantitiy;
        this.btcusdtPurchaseAmount = btcusdtPurchaseAmount;
        this.ethusdtPurchaseAmount = ethusdtPurchaseAmount;
        this.xrpusdtPurchaseAmount = xrpusdtPurchaseAmount;
        this.adausdtPurchaseAmount = adausdtPurchaseAmount;
        this.transactions = transactions;
    }
}