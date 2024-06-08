package com.studyCycle.StudyCycle.Repository;

import com.studyCycle.StudyCycle.Payload.PasswordResetRequest;
import com.studyCycle.StudyCycle.entity.User;
import io.jsonwebtoken.Claims;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    User findByVerificationCode(String verificationCode);
    Optional<User> findByEmailAndResetCode(String email, String resetCode);
}
