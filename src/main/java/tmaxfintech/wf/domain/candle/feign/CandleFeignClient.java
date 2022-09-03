package tmaxfintech.wf.domain.candle.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "CandleFeignClient", url = "https://www.binance.com/api/v3/klines")
public interface CandleFeignClient {

    @GetMapping
    Optional<List<List<String>>> getStringCandle2dList(@RequestParam(value="symbol") String symbol, @RequestParam(value="interval") String interval, @RequestParam(value="limit") Integer limit);
}
