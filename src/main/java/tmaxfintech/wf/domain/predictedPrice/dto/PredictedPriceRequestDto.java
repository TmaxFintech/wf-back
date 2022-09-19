package tmaxfintech.wf.domain.predictedPrice.dto;


public class PredictedPriceRequestDto {
    private String symbol;
    private Double predictedPrice;

    public String getSymbol(){
        return symbol;
    }
    public Double getPredictedPrice(){
        return predictedPrice;
    }
}

