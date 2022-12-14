package tmaxfintech.wf.domain.transactions.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tmaxfintech.wf.domain.transactions.dto.TransactionsResponseDto;
import tmaxfintech.wf.domain.transactions.entity.Transactions;
import tmaxfintech.wf.domain.transactions.repository.TransactionsRepository;
import tmaxfintech.wf.domain.user.entity.User;
import tmaxfintech.wf.domain.user.repository.UserRepository;
import tmaxfintech.wf.exception.UserNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionsService {

    private final TransactionsRepository transactionsRepository;

    private final UserRepository userRepository;

    @Value("${coinResponse.side.sell}")
    private String SELL;

    @Value("${coinResponse.side.buy}")
    private String BUY;

    @Value("${responseMessage.USER_NOT_FOUND}")

    private String USER_NOT_FOUND;

    public TransactionsService(TransactionsRepository transactionsRepository, UserRepository userRepository) {
        this.transactionsRepository = transactionsRepository;
        this.userRepository = userRepository;
    }

    public Page<TransactionsResponseDto> retrieveTransactionsPage(String symbol, String side, Pageable pageable, String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));
        if (side != null) {
            if (symbol != null) {
                Page<Transactions> transactions = transactionsRepository.findAllByCoinAccountAndSideAndSymbol(pageable, user.getCoinAccount(), side, symbol);
                return new PageImpl<>(transactions.stream().map(Transactions::toDto).collect(Collectors.toList()), pageable, transactions.getTotalElements());
            }
            Page<Transactions> transactions = transactionsRepository.findAllByCoinAccountAndSide(pageable, user.getCoinAccount(), side);
            return new PageImpl<>(transactions.stream().map(Transactions::toDto).collect(Collectors.toList()), pageable, transactions.getTotalElements());
        }
        if (symbol != null) {
            Page<Transactions> transactions = transactionsRepository.findAllByCoinAccountAndSymbol(pageable, user.getCoinAccount(), symbol);
            return new PageImpl<>(transactions.stream().map(Transactions::toDto).collect(Collectors.toList()), pageable, transactions.getTotalElements());
        }
        Page<Transactions> transactions = transactionsRepository.findAllByCoinAccount(pageable, user.getCoinAccount());
        return new PageImpl<>(transactions.stream().map(Transactions::toDto).collect(Collectors.toList()), pageable, transactions.getTotalElements());
    }
}
