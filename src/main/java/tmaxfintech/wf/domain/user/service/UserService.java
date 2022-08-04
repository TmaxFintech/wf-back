package tmaxfintech.wf.domain.user.service;

import org.springframework.http.ResponseEntity;
import tmaxfintech.wf.domain.user.dto.JoinRequestDto;
import tmaxfintech.wf.util.response.DefaultResponse;

public interface UserService {

    ResponseEntity<DefaultResponse> join(JoinRequestDto joinRequestDto);

}
