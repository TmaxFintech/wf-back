package tmaxfintech.wf.domain.coin.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tmaxfintech.wf.domain.coin.service.CoinService;
import tmaxfintech.wf.util.response.DefaultResponse;

@RestController
public class CoinApiController {

    private final CoinService coinService;


    @Value("${responseMessage.SELECT_COIN_SUCCESS}")
    private String SELECT_COIN_SUCCESS;

    public CoinApiController(CoinService coinService) {
        this.coinService = coinService;
    }

    @GetMapping("/coins")
    public ResponseEntity<DefaultResponse> selectCoins() {
        return new ResponseEntity(DefaultResponse.response(HttpStatus.OK.value(), SELECT_COIN_SUCCESS, coinService.selectCoins()), HttpStatus.OK);
    }
}
