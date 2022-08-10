package tmaxfintech.wf.domain.coin.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tmaxfintech.wf.domain.coin.entity.Coin;
import tmaxfintech.wf.domain.coin.feign.CoinFeignClient;
import tmaxfintech.wf.domain.coin.repository.CoinRepository;
import tmaxfintech.wf.exception.BinanceCoinApiException;

@Service
public class CoinService {

    private final CoinFeignClient coinFeignClient;
    private final CoinRepository coinRepository;

    public CoinService(CoinFeignClient coinFeignClient, CoinRepository coinRepository) {
        this.coinFeignClient = coinFeignClient;
        this.coinRepository = coinRepository;
    }

    public Coin getCoin(String symbol) {
        return coinFeignClient.getCoin(symbol).orElseThrow(() -> new BinanceCoinApiException()).toEntity();
    }

    @Transactional
    public void updateCoin(String symbol) {
        Coin coin = coinRepository.findBySymbol(symbol).get();
        coin.updatePrice(getCoin(symbol).getPrice());
    }
}
