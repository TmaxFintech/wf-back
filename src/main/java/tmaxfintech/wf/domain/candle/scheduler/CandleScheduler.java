package tmaxfintech.wf.domain.candle.scheduler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tmaxfintech.wf.domain.candle.service.CandleService;

@Component
public class CandleScheduler {

    private final CandleService candleService;

    @Value("${binance.symbol.btcusdt}")
    private String symbolBtcusdt;

    @Value("${binance.symbol.ethusdt}")
    private String symbolEthusdt;

    @Value("${binance.symbol.xrpusdt}")
    private String symbolXrpusdt;

    @Value("${binance.symbol.adausdt}")
    private String symbolAdausdt;

    @Value("${binance.interval.1m}")
    private String interval;

    @Value("${binance.limit.1}")
    private Integer limit;

    public CandleScheduler(CandleService candleService) {
        this.candleService = candleService;
    }

    @Scheduled(cron = "1 0/1 * * * *")
    public void saveCandle() {
        candleService.saveCandle(symbolBtcusdt, interval, limit);
        candleService.saveCandle(symbolEthusdt, interval, limit);
        candleService.saveCandle(symbolXrpusdt, interval, limit);
        candleService.saveCandle(symbolAdausdt, interval, limit);
    }
}
