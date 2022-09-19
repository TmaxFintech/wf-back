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

    public Page<TransactionsResponseDto> retrieveTransactionsPage(String side, Pageable pageable, String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));
        if (side != null) {
            return new PageImpl<>(transactionsRepository.findAllByCoinAccountAndSide(pageable, user.getCoinAccount(),
                    side).stream().map(Transactions::toDto).collect(Collectors.toList()));
        }
        return new PageImpl<>(transactionsRepository.findAllByCoinAccount(pageable, user.getCoinAccount())
                .stream().map(Transactions::toDto).collect(Collectors.toList()));
    }
}
