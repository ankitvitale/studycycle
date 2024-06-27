package com.studyCycle.StudyCycle.Repository;

import com.studyCycle.StudyCycle.Payload.PasswordResetRequest;
import com.studyCycle.StudyCycle.entity.User;
import io.jsonwebtoken.Claims;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>  {
    User findByEmail(String email);

    User findByVerificationCode(String verificationCode);
    Optional<User> findByEmailAndResetCode(String email, String resetCode);

    @Query("SELECT u FROM User u WHERE u.usertype = :usertype")
    List<User> findByUsertype(@Param("usertype") String usertype);

    @Query("SELECT u FROM User u WHERE u.verified = false")
    List<User> findUnverifiedUsers();
}
