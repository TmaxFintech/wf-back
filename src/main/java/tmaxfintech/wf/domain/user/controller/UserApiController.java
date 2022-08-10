package tmaxfintech.wf.domain.user.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tmaxfintech.wf.config.jwt.JwtProperty;
import tmaxfintech.wf.domain.user.dto.JoinRequestDto;
import tmaxfintech.wf.domain.user.dto.UpdateRequestDto;
import tmaxfintech.wf.domain.user.service.UserService;
import tmaxfintech.wf.util.response.DefaultResponse;

@RestController
public class UserApiController {

    private final UserService userService;

    public UserApiController(UserService userService) {
        this.userService = userService;
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
    ResponseEntity<DefaultResponse> updateUser(@RequestBody UpdateRequestDto updateRequestDto, @RequestHeader HttpHeaders headers) {
        String jwtToken = getJwtToken(headers);
        return userService.updatePassword(jwtToken, updateRequestDto.getPassword());
    }

    private String getJwtToken(HttpHeaders headers) {
        return headers.get("Authorization").get(0).replace(JwtProperty.TOKEN_PREFIX, "");
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
