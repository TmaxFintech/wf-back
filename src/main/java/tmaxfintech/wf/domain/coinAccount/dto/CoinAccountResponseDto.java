package tmaxfintech.wf.domain.coinAccount.dto;

import lombok.Builder;
import tmaxfintech.wf.domain.holdingCoin.dto.HoldingCoinResponseDto;

import java.util.ArrayList;
import java.util.List;

@Builder
public class CoinAccountResponseDto {

    private Double cash;

    private List<HoldingCoinResponseDto> holdingCoinResponseDtos = new ArrayList<>();

    public CoinAccountResponseDto(Double cash, List<HoldingCoinResponseDto> holdingCoinResponseDtos) {
        this.cash = cash;
        this.holdingCoinResponseDtos = holdingCoinResponseDtos;
    }

    public Double getCash() {
        return cash;
    }

    public List<HoldingCoinResponseDto> getHoldingCoinResponseDtos() {
        return holdingCoinResponseDtos;
    }
}
