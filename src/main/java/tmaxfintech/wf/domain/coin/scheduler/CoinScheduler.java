package tmaxfintech.wf.domain.coin.scheduler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tmaxfintech.wf.domain.coin.service.CoinService;

@Component
public class CoinScheduler {

    private final CoinService coinService;

    @Value("${binance.symbol.btcusdt}")
    private String symbolBtcusdt;

    @Value("${binance.symbol.ethusdt}")
    private String symbolEthusdt;

    @Value("${binance.symbol.xrpusdt}")
    private String symbolXrpusdt;

    @Value("${binance.symbol.adausdt}")
    private String symbolAdausdt;

    public CoinScheduler(CoinService coinService) {
        this.coinService = coinService;
    }

    @Scheduled(cron = "0/1 * * * * *")
    public void getCoin() {
        coinService.updateCoin(symbolBtcusdt);
        coinService.updateCoin(symbolEthusdt);
        coinService.updateCoin(symbolXrpusdt);
        coinService.updateCoin(symbolAdausdt);
    }
}

