package tmaxfintech.wf.domain.user.dto;

import tmaxfintech.wf.domain.user.entity.User;

public class JoinRequestDto {
    private String username;

    private String name;

    private String lastName;

    private String firstName;

    private String bankName;

    private String accountNumber;

    private String phoneNumber;

    private String password;

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getBankName() {
        return bankName;
    }

    public String getUsername() {
        return username;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public User ToEntity(JoinRequestDto joinRequestDto) {
        return User.builder()
                .bankName(joinRequestDto.getBankName())
                .accountNumber(joinRequestDto.getAccountNumber())
                .lastName(joinRequestDto.getLastName())
                .firstName(joinRequestDto.getFirstName())
                .name(joinRequestDto.getName())
                .phoneNumber(joinRequestDto.getPhoneNumber())
                .username(joinRequestDto.getUsername())
                .password(joinRequestDto.getPassword())
                .build();
    }
}