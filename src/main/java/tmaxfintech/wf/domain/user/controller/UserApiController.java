package tmaxfintech.wf.domain.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tmaxfintech.wf.domain.user.dto.JoinRequestDto;
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


    @GetMapping("/user")
    public String user() {
        return "user";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }
}
