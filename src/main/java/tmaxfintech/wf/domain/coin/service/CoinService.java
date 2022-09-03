package tmaxfintech.wf.domain.coin.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tmaxfintech.wf.domain.coin.dto.CoinFeignDto;
import tmaxfintech.wf.domain.coin.dto.CoinResponseDto;
import tmaxfintech.wf.domain.coin.entity.Coin;
import tmaxfintech.wf.domain.coin.feign.CoinFeignClient;
import tmaxfintech.wf.domain.coin.repository.CoinRepository;
import tmaxfintech.wf.exception.BinanceCoinApiException;
import tmaxfintech.wf.util.response.DefaultResponse;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CoinService {

    private final CoinFeignClient coinFeignClient;
    private final CoinRepository coinRepository;

    public CoinService(CoinFeignClient coinFeignClient, CoinRepository coinRepository) {
        this.coinFeignClient = coinFeignClient;
        this.coinRepository = coinRepository;
    }

    public CoinFeignDto getCoinFeignDto(String symbol) {
        return coinFeignClient.getCoinFeignDto(symbol).orElseThrow(() -> new BinanceCoinApiException());
    }

    @Transactional
    public void updateCoin(String symbol) {
        Coin coin = coinRepository.findBySymbol(symbol).orElseThrow(() -> new BinanceCoinApiException());
        coin.updateCoin(getCoinFeignDto(symbol));
    }

    @Transactional(readOnly = true)
    public List<CoinResponseDto> selectCoins() {
        List<Coin> coins = coinRepository.findAll();
        if (coins == null || coins.isEmpty()) throw new BinanceCoinApiException();
        List<CoinResponseDto> coinResponseDtos = coins.stream().map(Coin::toDto).collect(Collectors.toList());
        return coinResponseDtos;
    }
}
