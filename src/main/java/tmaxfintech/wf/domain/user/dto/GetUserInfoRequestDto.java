package tmaxfintech.wf.domain.user.dto;

import lombok.Builder;

@Builder
public class GetUserInfoRequestDto {
    private String username;
    private String name;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String bankName;
    private String accountNumber;

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() { return lastName; }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public GetUserInfoRequestDto(String username, String name, String firstName, String lastName, String phoneNumber, String bankName, String accountNumber) {
        this.username = username;
        this.name = name;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.bankName = bankName;
        this.accountNumber = accountNumber;
    }
}
