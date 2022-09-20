package tmaxfintech.wf.domain.transactions.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tmaxfintech.wf.domain.transactions.service.TransactionsService;
import tmaxfintech.wf.util.jwt.JwtUtility;
import tmaxfintech.wf.util.response.DefaultResponse;


@RestController
public class TransactionsController {

    private final TransactionsService transactionsService;

    private final JwtUtility jwtUtility;

    @Value("${responseMessage.GET_TRANSACTIONS_SUCCESS}")
    private String GET_TRANSACTIONS_SUCCESS;

    public TransactionsController(TransactionsService transactionsService, JwtUtility jwtUtility) {
        this.transactionsService = transactionsService;
        this.jwtUtility = jwtUtility;
    }

    @GetMapping("/transactions")
    public ResponseEntity<DefaultResponse> retrieveTransactionsPage(@RequestParam(required = false) String symbol, @RequestParam(required = false) String side, @RequestHeader HttpHeaders headers, @PageableDefault(size = 10, sort = "tradingTime", direction = Sort.Direction.DESC) Pageable pageable) {
        String username = jwtUtility.getUsernameFromJwtToken(jwtUtility.getJwtTokenFromHeader(headers));
        return new ResponseEntity(DefaultResponse.response(HttpStatus.OK.value(), GET_TRANSACTIONS_SUCCESS, transactionsService.retrieveTransactionsPage(symbol, side, pageable, username)), HttpStatus.OK);
    }
}
