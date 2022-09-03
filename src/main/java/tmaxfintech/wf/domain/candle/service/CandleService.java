package tmaxfintech.wf.domain.candle.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tmaxfintech.wf.domain.candle.dto.CandleResponseDto;
import tmaxfintech.wf.domain.candle.entity.Candle;
import tmaxfintech.wf.domain.candle.feign.CandleFeignClient;
import tmaxfintech.wf.domain.candle.repository.CandleRepository;
import tmaxfintech.wf.exception.BinanceCandleApiException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CandleService {

    private final CandleFeignClient candleFeignClient;
    private final CandleRepository candleRepository;

    public CandleService(CandleFeignClient candleFeignClient, CandleRepository candleRepository) {
        this.candleFeignClient = candleFeignClient;
        this.candleRepository = candleRepository;
    }

    @Transactional
    public void saveCandle(String symbol, String intervals, Integer limit) {
        List<String> stringCandleList = getStringCandleList(symbol, intervals, limit);
        candleRepository.save(createCandleWithStringCandleList(stringCandleList, symbol, intervals));
    }

    public List<String> getStringCandleList(String symbol, String interval, Integer limit) {
        return candleFeignClient.getStringCandle2dList(symbol, interval, limit).orElseThrow(() -> new BinanceCandleApiException()).get(0);
    }

    @Transactional(readOnly = true)
    public List<CandleResponseDto> selectCandles(String symbol, String intervals) {
        List<Candle> candles = candleRepository.findAllBySymbolAndIntervals(symbol, intervals);
        return candles.stream().map(Candle::toDto).collect(Collectors.toList());
    }

    private Candle createCandleWithStringCandleList(List<String> stringCandleList, String symbol, String intervals) {
        return Candle.builder()
                .openTime(Long.parseLong(stringCandleList.get(0)))
                .openPrice(Double.parseDouble(stringCandleList.get(1)))
                .highPrice(Double.parseDouble(stringCandleList.get(2)))
                .lowPrice(Double.parseDouble(stringCandleList.get(3)))
                .closePrice(Double.parseDouble(stringCandleList.get(4)))
                .volume(Double.parseDouble(stringCandleList.get(5)))
                .closeTime(Long.parseLong(stringCandleList.get(6)))
                .quoteAssetVolume(Double.parseDouble(stringCandleList.get(7)))
                .numberOfTrades(Long.parseLong(stringCandleList.get(8)))
                .takerBuyBaseAssetVolume(Double.parseDouble(stringCandleList.get(9)))
                .takerBuyQuoteAssetVolume(Double.parseDouble(stringCandleList.get(10)))
                .ignores(Double.parseDouble(stringCandleList.get(11)))
                .symbol(symbol)
                .intervals(intervals)
                .build();
    }
}
