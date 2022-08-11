package tmaxfintech.wf.domain.user.dto;

import lombok.Data;

@Data
public class LoginResponseDto {
    private String name;

    public LoginResponseDto(String name) {
        this.name = name;
    }
}
