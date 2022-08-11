package tmaxfintech.wf.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tmaxfintech.wf.domain.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByPhoneNumber(String phoneNumber);
    User findByUsername(String username);
    User findByBankNameAndAccountNumber(String bankName, String accountNumber);
}
