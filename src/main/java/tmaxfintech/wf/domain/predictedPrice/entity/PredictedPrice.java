package tmaxfintech.wf.domain.predictedPrice.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PredictedPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String symbol;
    private Double predictedPrice;
    private String intervals;

    protected PredictedPrice(){

    }

    public PredictedPrice(String symbol, Double predictedPrice, String intervals){
        this.symbol = symbol;
        this.predictedPrice = predictedPrice;
        this.intervals = intervals;
    }

    public String getSymbol(){return symbol;}
    public Double getPredictedPrice(){return predictedPrice;}

    public void updatePredictedPrice(String symbol, Double predictedPrice, String intervals){
        this.symbol = symbol;
        this.predictedPrice = predictedPrice;
        this.intervals = intervals;
    }

}
