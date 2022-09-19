package tmaxfintech.wf.domain.coinAccount.dto;

public class OrderRequestDto {

    private String symbol;

    private String side;

    private Double volume;

    private Double price;

    private OrderRequestDto() {
    }

    public String getSymbol() {
        return symbol;
    }

    public String getSide() {
        return side;
    }

    public Double getVolume() {
        return volume;
    }

    public Double getPrice() {
        return price;
    }
}
