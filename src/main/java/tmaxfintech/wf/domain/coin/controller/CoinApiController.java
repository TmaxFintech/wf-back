package tmaxfintech.wf.domain.coin.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tmaxfintech.wf.domain.coin.service.CoinService;
import tmaxfintech.wf.util.response.DefaultResponse;

@RestController
public class CoinApiController {

    private final CoinService coinService;

    public CoinApiController(CoinService coinService) {
        this.coinService = coinService;
    }

    @GetMapping("/coins")
    public ResponseEntity<DefaultResponse> selectCoins() {
        return coinService.selectCoins();
    }
}
