package com.studyCycle.StudyCycle.Repository;

import com.studyCycle.StudyCycle.Payload.PasswordResetRequest;
import com.studyCycle.StudyCycle.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmailOrPhoneNumber(String email, String phoneNumber);

    User findByVerificationCode(String verificationCode);

    Optional<User> findByEmail(String email);
    Optional<User> findByEmailAndResetCode(String email, String resetCode);


//    Optional<User> findByEmailOrPhone(String emailOrPhone);
//    Optional<User> findByEmailOrPhoneAndResetCode(String emailOrPhone, String resetCode);
}
