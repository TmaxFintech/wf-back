package tmaxfintech.wf.domain.user.service.impl;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import tmaxfintech.wf.domain.user.dto.JoinRequestDto;
import tmaxfintech.wf.domain.user.entity.User;
import tmaxfintech.wf.domain.user.entity.UserRoleType;
import tmaxfintech.wf.exception.UserNotFoundException;
import tmaxfintech.wf.domain.user.repository.UserRepository;
import tmaxfintech.wf.domain.user.service.UserService;
import tmaxfintech.wf.util.jwt.JwtUtility;
import tmaxfintech.wf.util.response.DefaultResponse;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final JwtUtility jwtUtility;

    @Value("${responseMessage.UPDATE_USER_FAIL}")
    private String UPDATE_USER_FAIL;
    @Value("${responseMessage.UPDATE_USER_SUCCESS}")
    private String UPDATE_USER_SUCCESS;

    @Value("${responseMessage.EXISTED_USERNAME}")
    private String EXISTED_USERNAME;

    @Value("${responseMessage.EXISTED_ACCOUNT}")
    private String EXISTED_ACCOUNT;

    @Value("${responseMessage.EXISTED_PHONENUMBER}")
    private String EXISTED_PHONENUMBER;

    @Value("${responseMessage.JOIN_SUCCESS}")
    private String JOIN_SUCCESS;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtility jwtUtility) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtility = jwtUtility;
    }

    @Transactional
    @Override
    @ConfigurationProperties("responseMessage")
    public ResponseEntity<DefaultResponse> join(@RequestBody JoinRequestDto joinRequestDto) {
        return joinAfterCheckExistence(joinRequestDto);
    }

    private ResponseEntity joinAfterCheckExistence(JoinRequestDto joinRequestDto) {
        if(!userRepository.findByUsername(joinRequestDto.getUsername()).equals(Optional.empty())){
            return new ResponseEntity(DefaultResponse.response(HttpStatus.CONFLICT.value(), EXISTED_USERNAME), HttpStatus.CONFLICT);
        }else if(!userRepository.findByBankNameAndAccountNumber(joinRequestDto.getBankName(), joinRequestDto.getAccountNumber()).equals(Optional.empty())){
            return new ResponseEntity(DefaultResponse.response(HttpStatus.CONFLICT.value(), EXISTED_ACCOUNT), HttpStatus.CONFLICT);
        }else if(!userRepository.findByPhoneNumber(joinRequestDto.getPhoneNumber()).equals(Optional.empty())){
            return new ResponseEntity(DefaultResponse.response(HttpStatus.CONFLICT.value(), EXISTED_PHONENUMBER), HttpStatus.CONFLICT);
        } return joinUser(joinRequestDto);

    }

    private ResponseEntity joinUser(JoinRequestDto joinRequestDto) {
        User userEntity = createUser(joinRequestDto);
        userRepository.save(userEntity);

        return new ResponseEntity(DefaultResponse.response(HttpStatus.OK.value(), JOIN_SUCCESS), HttpStatus.OK);
    }

    private User createUser(JoinRequestDto joinRequestDto) {
        User userEntity = joinRequestDto.ToEntity(joinRequestDto);
        userEntity.setPasswordandUserRoleTypeandJoinDate(passwordEncoder.encode(joinRequestDto.getPassword()),
                UserRoleType.ROLE_USER, new Timestamp(System.currentTimeMillis()));
        return userEntity;
    }

    @Transactional
    public ResponseEntity<DefaultResponse> updatePassword(String jwtToken, String rawPassword) {
        String username = jwtUtility.getUsernameFromJwtToken(jwtToken);
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("User Not Found"));
        if (isMatchedPassword(rawPassword, user.getPassword())) {
            return new ResponseEntity(DefaultResponse.response(HttpStatus.CONFLICT.value(), UPDATE_USER_FAIL), HttpStatus.CONFLICT);
        }
        user.changePassword(passwordEncoder.encode(rawPassword));
        return new ResponseEntity(DefaultResponse.response(HttpStatus.OK.value(), UPDATE_USER_SUCCESS), HttpStatus.OK);
    }

    private boolean isMatchedPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    public JwtUtility getJwtUtility() {
        return jwtUtility;
    }
}
