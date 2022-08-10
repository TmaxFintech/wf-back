package tmaxfintech.wf.domain.candle.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tmaxfintech.wf.domain.candle.entity.Candle;
import tmaxfintech.wf.domain.candle.feign.CandleFeignClient;
import tmaxfintech.wf.domain.candle.repository.CandleRepository;
import tmaxfintech.wf.exception.BinanceCandleApiException;

import java.util.List;

@Service
public class CandleService {

    private final CandleFeignClient candleFeignClient;
    private final CandleRepository candleRepository;

    public CandleService(CandleFeignClient candleFeignClient, CandleRepository candleRepository) {
        this.candleFeignClient = candleFeignClient;
        this.candleRepository = candleRepository;
    }

    @Transactional
    public void saveCandle(String symbol, String interval, Integer limit) {
        candleRepository.save(createCandleWithCandleList(getCandleList(symbol, interval, limit)));
    }

    public List<String> getCandleList(String symbol, String interval, Integer limit) {
        return candleFeignClient.getCandle2dList(symbol, interval, limit).orElseThrow(() -> new BinanceCandleApiException()).get(0);
    }

    private Candle createCandleWithCandleList(List<String> candleList) {
        return Candle.builder()
                .openTime(Double.parseDouble(candleList.get(0)))
                .open(Double.parseDouble(candleList.get(1)))
                .high(Double.parseDouble(candleList.get(2)))
                .low(Double.parseDouble(candleList.get(3)))
                .close(Double.parseDouble(candleList.get(4)))
                .volume(Double.parseDouble(candleList.get(5)))
                .closeTime(Long.parseLong(candleList.get(6)))
                .quoteAssetVolume(Double.parseDouble(candleList.get(7)))
                .numberOfTrades(Long.parseLong(candleList.get(8)))
                .takerBuyBaseAssetVolume(Double.parseDouble(candleList.get(9)))
                .takerBuyQuoteAssetVolume(Double.parseDouble(candleList.get(10)))
                .ignores(Double.parseDouble(candleList.get(11)))
                .build();
    }
}
