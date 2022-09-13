package tmaxfintech.wf.domain.user.entity;

import lombok.Builder;
import org.hibernate.annotations.CreationTimestamp;
import tmaxfintech.wf.domain.user.dto.LoginResponseDto;
import tmaxfintech.wf.domain.user.dto.GetUserInfoRequestDto;

import javax.persistence.*;
import java.sql.Timestamp;

@Builder
@Entity
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String name;

    private String lastName;

    private String firstName;

    private String bankName;

    private String accountNumber;

    private String phoneNumber;

    private String password;

    public User(Long id, String username, String name, String lastName, String firstName, String bankName, String accountNumber, String phoneNumber, String password, UserRoleType userRoleType, Timestamp joinDate) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.lastName = lastName;
        this.firstName = firstName;
        this.bankName = bankName;
        this.accountNumber = accountNumber;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.userRoleType = userRoleType;
        this.joinDate = joinDate;
    }

    @Enumerated(EnumType.STRING)
    private UserRoleType userRoleType;

    @CreationTimestamp
    private Timestamp joinDate;

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public UserRoleType getUserRoleType() {
        return userRoleType;
    }

    public User() {
    }

    public void setJoinDate(Timestamp joinDate) {
        this.joinDate = joinDate;
    }

    public void setUserRoleType(UserRoleType userRoleType) {
        this.userRoleType = userRoleType;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPasswordandUserRoleTypeandJoinDate(String password, UserRoleType userRoleType, Timestamp joinDate) {
        setPassword(password);
        setUserRoleType(userRoleType);
        setJoinDate(joinDate);
    }

    public void changePassword(String password) {
        setPassword(password);
    }

    public LoginResponseDto toLoginResponseDto() {
        return new LoginResponseDto(name);
    }

    public GetUserInfoRequestDto toGetUserInfoRequestDto() {
        return GetUserInfoRequestDto.builder()
                .username(username)
                .name(name)
                .firstName(firstName)
                .lastName(lastName)
                .phoneNumber(phoneNumber)
                .bankName(bankName)
                .accountNumber(accountNumber)
                .build();
    }
}