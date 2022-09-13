package tmaxfintech.wf.domain.coinAccount.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tmaxfintech.wf.domain.coinAccount.dto.CoinAccountResponseDto;
import tmaxfintech.wf.domain.coinAccount.entity.CoinAccount;
import tmaxfintech.wf.domain.coinAccount.repository.CoinAccountRepository;
import tmaxfintech.wf.exception.CoinAccountNullPointerException;

@Service
public class CoinAccountService {

    private final CoinAccountRepository coinAccountRepository;

    @Value("${holdingCoin.defaultCash}")
    private Double defaultCash;

    public CoinAccountService(CoinAccountRepository coinAccountRepository) {
        this.coinAccountRepository = coinAccountRepository;
    }

    @Transactional(readOnly = true)
    public CoinAccountResponseDto retrieveCoinAccountDto(String username) {
        return coinAccountRepository.findWithUsername(username).orElseThrow(() -> new CoinAccountNullPointerException()).toDto();
    }

    public CoinAccount createCoinAccount() {
        return new CoinAccount(defaultCash);
    }
}
