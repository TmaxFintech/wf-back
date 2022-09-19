package tmaxfintech.wf.domain.predictedPrice.dto;

public class PredictedPriceResponseDto {
    private String symbol;
    private Double predictedPrice;

    private String intervals;

    public PredictedPriceResponseDto(String symbol, Double predictedPrice, String intervals) {
        this.symbol = symbol;
        this.predictedPrice = predictedPrice;
        this.intervals = intervals;
    }

    public String getSymbol(){
        return symbol;
    }
    public Double getPredictedPrice(){
        return predictedPrice;
    }

    public String getIntervals() {
        return intervals;
    }
}
