package tmaxfintech.wf.domain.coin.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tmaxfintech.wf.domain.coin.dto.CoinFeignDto;

import java.util.Optional;

@FeignClient(name = "CoinFeignClient", url = "https://www.binance.com/api/v3/ticker")
public interface CoinFeignClient {

    @GetMapping
    Optional<CoinFeignDto> getCoin(@RequestParam(value="symbol") String symbol);
}
