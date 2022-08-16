package tmaxfintech.wf.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tmaxfintech.wf.domain.user.entity.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByPhoneNumber(String phoneNumber);
    Optional<User> findByUsername(String username);
    Optional<User> findByBankNameAndAccountNumber(String bankName,String accountNumber);
}
