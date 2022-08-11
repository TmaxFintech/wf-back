package tmaxfintech.wf.domain.user.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import tmaxfintech.wf.config.jwt.JwtProperty;
import tmaxfintech.wf.domain.user.dto.JoinRequestDto;
import tmaxfintech.wf.domain.user.entity.User;
import tmaxfintech.wf.domain.user.entity.UserRoleType;
import tmaxfintech.wf.domain.user.repository.UserRepository;
import tmaxfintech.wf.domain.user.service.UserService;
import tmaxfintech.wf.util.response.DefaultResponse;
import tmaxfintech.wf.util.response.ResponseMessage;

import java.sql.Timestamp;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${responseMessage.UPDATE_USER_FAIL}")
    private String UPDATE_USER_FAIL;
    @Value("${responseMessage.UPDATE_USER_SUCCESS}")
    private String UPDATE_USER_SUCCESS;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    @ConfigurationProperties("responseMessage")
    public ResponseEntity<DefaultResponse> join(@RequestBody JoinRequestDto joinRequestDto) {
        ResponseEntity responseEntity = checkDuplication(joinRequestDto);
        if (responseEntity != null) return responseEntity;

        User userEntity = createUser(joinRequestDto);
        userRepository.save(userEntity);

        return new ResponseEntity(DefaultResponse.response(HttpStatus.OK.value(), ResponseMessage.JOIN_SUCCESS, null), HttpStatus.OK);
    }

    private ResponseEntity checkDuplication(JoinRequestDto joinRequestDto) {
        if (isDuplicatedUsername(userRepository.findByUsername(joinRequestDto.getUsername()))) {
            return new ResponseEntity(DefaultResponse.response(HttpStatus.CONFLICT.value(), ResponseMessage.DUPLICATED_USERNAME, null), HttpStatus.CONFLICT);
        } else if (isDuplicatedAccountNumber(userRepository.findByAccountNumber(joinRequestDto.getAccountNumber()))) {
            return new ResponseEntity(DefaultResponse.response(HttpStatus.CONFLICT.value(), ResponseMessage.DUPLICATED_ACCOUNTNUMBER, null), HttpStatus.CONFLICT);
        } else if (isDuplicatedPhoneNumber(userRepository.findByPhoneNumber(joinRequestDto.getPhoneNumber()))) {
            return new ResponseEntity(DefaultResponse.response(HttpStatus.CONFLICT.value(), ResponseMessage.DUPLICATED_PHONENUMBER, null), HttpStatus.CONFLICT);
        }
        return null;
    }

    private boolean isDuplicatedPhoneNumber(User user) {
        return user != null;
    }

    private boolean isDuplicatedAccountNumber(User user) {
        return user != null;
    }

    private boolean isDuplicatedUsername(User user) {
        return user != null;
    }

    private User createUser(JoinRequestDto joinRequestDto) {
        User userEntity = joinRequestDto.toEntity();
        userEntity.setPasswordandUserRoleTypeandJoinDate(passwordEncoder.encode(joinRequestDto.getPassword()),
                UserRoleType.ROLE_USER, new Timestamp(System.currentTimeMillis()));
        return userEntity;
    }

    @Transactional
    public ResponseEntity<DefaultResponse> updatePassword(String jwtToken, String rawPassword) {
        String username = getUsernamefromJwtToken(jwtToken);
        User user = userRepository.findByUsername(username);
        if (isMatchedPassword(rawPassword, user.getPassword())) {
            return new ResponseEntity(DefaultResponse.response(HttpStatus.CONFLICT.value(), UPDATE_USER_FAIL, null), HttpStatus.CONFLICT);
        }
        user.changePassword(passwordEncoder.encode(rawPassword));
        return new ResponseEntity(DefaultResponse.response(HttpStatus.OK.value(), UPDATE_USER_SUCCESS, null), HttpStatus.OK);
    }

    private boolean isMatchedPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    private String getUsernamefromJwtToken(String jwtToken) {
        return JWT.require(Algorithm.HMAC512(JwtProperty.SECRET)).build().verify(jwtToken).getClaim("username").asString();
    }
}
