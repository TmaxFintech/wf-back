package tmaxfintech.wf.domain.user.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tmaxfintech.wf.domain.user.dto.JoinRequestDto;
import tmaxfintech.wf.domain.user.dto.UpdateRequestDto;
import tmaxfintech.wf.domain.user.service.UserService;
import tmaxfintech.wf.util.jwt.JwtUtility;
import tmaxfintech.wf.util.response.DefaultResponse;

@RestController
public class UserApiController {

    private final UserService userService;
    private final JwtUtility jwtUtility;

    public UserApiController(UserService userService, JwtUtility jwtUtility) {
        this.userService = userService;
        this.jwtUtility = jwtUtility;
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @PostMapping("/users/join")
    public ResponseEntity<DefaultResponse> join(@RequestBody JoinRequestDto joinRequestDto) {
        return userService.join(joinRequestDto);
    }

    @PutMapping("/users")
    public ResponseEntity<DefaultResponse> updateUser(@RequestBody UpdateRequestDto updateRequestDto, @RequestHeader HttpHeaders headers) {
        String jwtToken = jwtUtility.getJwtTokenFromHeader(headers);
        return userService.updatePassword(jwtToken, updateRequestDto.getPassword());
    }

    @GetMapping("/user")
    public String user() {
        return "user";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }
}
