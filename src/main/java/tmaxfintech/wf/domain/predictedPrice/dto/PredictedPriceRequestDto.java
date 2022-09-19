package tmaxfintech.wf.domain.predictedPrice.dto;


public class PredictedPriceRequestDto {
    private String symbol;
    private Double predictedPrice;
    private String intervals;

    public String getSymbol(){
        return symbol;
    }
    public Double getPredictedPrice(){
        return predictedPrice;
    }
    public String getIntervals(){return intervals;}
}

