package tmaxfintech.wf.domain.coinAccount.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import tmaxfintech.wf.domain.coinAccount.service.CoinAccountService;
import tmaxfintech.wf.util.jwt.JwtUtility;
import tmaxfintech.wf.util.response.DefaultResponse;

@RestController
public class CoinAccountController {

    private final CoinAccountService coinAccountService;

    private final JwtUtility jwtUtility;

    @Value("${responseMessage.SELECT_COINACCOUNT_SUCCESS}")
    private String SELECT_COINACCOUNT_SUCCESS;

    public CoinAccountController(CoinAccountService coinAccountService, JwtUtility jwtUtility) {
        this.coinAccountService = coinAccountService;
        this.jwtUtility = jwtUtility;
    }

    @GetMapping("/users/account")
    public ResponseEntity<DefaultResponse> retrieveCoinAccountDto(@RequestHeader HttpHeaders headers) {
        String username = jwtUtility.getUsernameFromJwtToken(jwtUtility.getJwtTokenFromHeader(headers));
        return new ResponseEntity(DefaultResponse.response(HttpStatus.OK.value(), SELECT_COINACCOUNT_SUCCESS, coinAccountService.retrieveCoinAccountDto(username)), HttpStatus.OK);
    }
}
