package tmaxfintech.wf.domain.coinAccount.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tmaxfintech.wf.domain.coin.repository.CoinRepository;
import tmaxfintech.wf.domain.coinAccount.dto.CoinAccountResponseDto;
import tmaxfintech.wf.domain.coinAccount.dto.OrderRequestDto;
import tmaxfintech.wf.domain.coinAccount.entity.CoinAccount;
import tmaxfintech.wf.domain.coinAccount.repository.CoinAccountRepository;
import tmaxfintech.wf.domain.holdingCoin.entity.HoldingCoin;
import tmaxfintech.wf.domain.holdingCoin.repository.HoldingCoinRepository;
import tmaxfintech.wf.domain.transactions.entity.Transactions;
import tmaxfintech.wf.domain.transactions.repository.TransactionsRepository;
import tmaxfintech.wf.exception.CoinAccountNullPointerException;
import tmaxfintech.wf.util.response.DefaultResponse;

@Service
public class CoinAccountService {

    private final CoinAccountRepository coinAccountRepository;

    private final CoinRepository coinRepository;

    private final HoldingCoinRepository holdingCoinRepository;

    private final TransactionsRepository transactionsRepository;

    @Value("${holdingCoin.defaultCash}")
    private Double DEFAULTCASH;

    @Value("${responseMessage.INCORRECT_SIDE_REQUEST}")
    private String INCORRECT_SIDE_REQUEST;

    @Value("${responseMessage.INCORRECT_SYMBOL_REQUEST}")
    private String INCORRECT_SYMBOL_REQUEST;

    @Value("${responseMessage.INSUFFICIENT_FUNDS_BUY}")
    private String INSUFFICIENT_FUNDS_BUY;

    @Value("${responseMessage.INSUFFICIENT_FUNDS_SELL}")
    private String INSUFFICIENT_FUNDS_SELL;

    @Value("${responseMessage.SUCCESS_BUY}")
    private String SUCCESS_BUY;

    @Value("${responseMessage.SUCCESS_SELL}")
    private String SUCCESS_SELL;

    @Value("${coinResponse.side.sell}")
    private String SELL;

    @Value("${coinResponse.side.buy}")
    private String BUY;

    @Value("${binance.symbol.btcusdt}")
    private String BTCUSDT;

    @Value("${binance.symbol.ethusdt}")
    private String ETHUSDT;

    @Value("${binance.symbol.xrpusdt}")
    private String XRPUSDT;

    @Value("${binance.symbol.adausdt}")
    private String ADAUSDT;

    public CoinAccountService(CoinAccountRepository coinAccountRepository, CoinRepository coinRepository, HoldingCoinRepository holdingCoinRepository, TransactionsRepository transactionsRepository) {
        this.coinAccountRepository = coinAccountRepository;
        this.coinRepository = coinRepository;
        this.holdingCoinRepository = holdingCoinRepository;
        this.transactionsRepository = transactionsRepository;
    }

    @Transactional(readOnly = true)
    public CoinAccountResponseDto retrieveCoinAccountDto(String username) {
        return coinAccountRepository.findWithUsername(username).orElseThrow(() -> new CoinAccountNullPointerException()).toDto();
    }

    @Transactional
    public ResponseEntity<DefaultResponse> orderCoins(String username, OrderRequestDto orderRequestDto) {
        CoinAccount coinAccount = coinAccountRepository.findWithUsername(username).orElseThrow(() -> new CoinAccountNullPointerException());
        if (!isRightSymbol(orderRequestDto.getSymbol())) return new ResponseEntity(DefaultResponse.response(HttpStatus.BAD_REQUEST.value(),
                INCORRECT_SYMBOL_REQUEST), HttpStatus.BAD_REQUEST);
        if (isRightSide(orderRequestDto.getSide())) {
            if (isBuySide(orderRequestDto.getSide())) {
                return buyCoins(coinAccount, orderRequestDto.getPrice(), orderRequestDto.getSymbol(), orderRequestDto.getVolume());
            } else {
                return sellCoins(coinAccount, orderRequestDto.getPrice(), orderRequestDto.getSymbol(), orderRequestDto.getVolume());
            }
        }
        return new ResponseEntity(DefaultResponse.response(HttpStatus.BAD_REQUEST.value(), INCORRECT_SIDE_REQUEST), HttpStatus.BAD_REQUEST);
    }

    private boolean isRightSymbol(String symbol) {
        return symbol.equals(BTCUSDT) || symbol.equals(ETHUSDT) || symbol.equals(XRPUSDT) || symbol.equals(ADAUSDT);
    }

    private ResponseEntity<DefaultResponse> buyCoins(CoinAccount coinAccount, Double price, String symbol, Double volume) {
        Double totalOrderPrice = price * volume;
        if (totalOrderPrice > coinAccount.getCash()) {
            return new ResponseEntity(DefaultResponse.response(HttpStatus.BAD_REQUEST.value(), INSUFFICIENT_FUNDS_BUY), HttpStatus.BAD_REQUEST);
        }
        coinAccount.changeCash(coinAccount.getCash() - totalOrderPrice);
        HoldingCoin holdingCoin = getHoldingCoinFromCoinAccount(coinAccount, symbol);
        if (holdingCoin != null) {
            holdingCoin.changeQuantity(holdingCoin.getQuantity() + volume);
            holdingCoin.changeTotalPurchasePrice(holdingCoin.getTotalPurchasePrice() + totalOrderPrice);
        }
        else {
            HoldingCoin newHoldingCoin = new HoldingCoin(symbol, volume, totalOrderPrice, coinAccount);
            coinAccount.getHoldingCoins().add(newHoldingCoin);
            holdingCoinRepository.save(newHoldingCoin);
        }
        Transactions transactions = new Transactions(symbol, volume, System.currentTimeMillis(), BUY, coinAccount, price);
        transactionsRepository.save(transactions);
        return new ResponseEntity(DefaultResponse.response(HttpStatus.CREATED.value(), SUCCESS_BUY), HttpStatus.CREATED);
    }

    private ResponseEntity<DefaultResponse> sellCoins(CoinAccount coinAccount, Double price, String symbol, Double volume) {
        HoldingCoin holdingCoin = getHoldingCoinFromCoinAccount(coinAccount, symbol);
        if (holdingCoin == null || holdingCoin.getQuantity() < volume) {
            return new ResponseEntity(DefaultResponse.response(HttpStatus.BAD_REQUEST.value(), INSUFFICIENT_FUNDS_SELL), HttpStatus.BAD_REQUEST);
        }
        holdingCoin.changeTotalPurchasePrice((holdingCoin.getTotalPurchasePrice() / holdingCoin.getQuantity()) * (holdingCoin.getQuantity() - volume));
        holdingCoin.changeQuantity(holdingCoin.getQuantity() - volume);
        if (holdingCoin.getQuantity() == 0) {
            holdingCoinRepository.delete(holdingCoin);
        }
        Transactions transactions = new Transactions(symbol, volume, System.currentTimeMillis(), SELL, coinAccount, price);
        transactionsRepository.save(transactions);
        coinAccount.changeCash(coinAccount.getCash() + (price * volume));
        return new ResponseEntity(DefaultResponse.response(HttpStatus.CREATED.value(), SUCCESS_SELL), HttpStatus.CREATED);
    }

    private HoldingCoin getHoldingCoinFromCoinAccount(CoinAccount coinAccount, String symbol) {
        for (HoldingCoin h : coinAccount.getHoldingCoins()) {
            if (h.getSymbol().equals(symbol)) {
                return h;
            }
        }
        return null;
    }

    private Boolean isBuySide(String side) {
        return side.equals(BUY);
    }

    private Boolean isRightSide(String side) {
        return side.equals(BUY) || side.equals(SELL);
    }

    public CoinAccount createCoinAccount() {
        return new CoinAccount(DEFAULTCASH);
    }
}
