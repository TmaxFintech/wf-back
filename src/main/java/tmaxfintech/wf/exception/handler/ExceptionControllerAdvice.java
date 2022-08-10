package tmaxfintech.wf.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tmaxfintech.wf.exception.BinanceCandleApiException;
import tmaxfintech.wf.exception.BinanceCoinApiException;
import tmaxfintech.wf.util.response.DefaultResponse;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(BinanceCandleApiException.class)
    public ResponseEntity<DefaultResponse> binanceCandleExceptionHandle(BinanceCandleApiException e) {
        return new ResponseEntity(DefaultResponse.response(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Binance Candle API 오류", null), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BinanceCoinApiException.class)
    public ResponseEntity<DefaultResponse> binanceCoinExceptionHandle(BinanceCoinApiException e) {
        return new ResponseEntity(DefaultResponse.response(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Binance Coin API 오류", null), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
