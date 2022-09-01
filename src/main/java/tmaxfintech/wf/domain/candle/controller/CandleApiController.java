package tmaxfintech.wf.domain.candle.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tmaxfintech.wf.domain.candle.service.CandleService;
import tmaxfintech.wf.util.response.DefaultResponse;

@RestController
public class CandleApiController {

    private final CandleService candleService;

    @Value("${responseMessage.SELECT_CANDLE_SUCCESS}")
    private String SELECT_CANDLE_SUCCESS;

    public CandleApiController(CandleService candleService) {
        this.candleService = candleService;
    }

    @GetMapping("/candles")
    public ResponseEntity<DefaultResponse> selectCandles(@RequestParam String symbol, @RequestParam String intervals) {
        return new ResponseEntity(DefaultResponse.response(HttpStatus.OK.value(), SELECT_CANDLE_SUCCESS, candleService.selectCandles(symbol, intervals)), HttpStatus.OK);
    }
}
