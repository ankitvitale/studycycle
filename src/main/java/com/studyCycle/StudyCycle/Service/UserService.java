package com.studyCycle.StudyCycle.Service;

import com.studyCycle.StudyCycle.Repository.UserRepository;
import com.studyCycle.StudyCycle.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService; // A service to send emails



  // private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//    @Autowired
//    private SmsService smsService;

    public User registerUser(String email, String phoneNumber) {
        User user = new User();
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setVerificationCode(generateOtp());
        user.setVerified(false);
        userRepository.save(user);

        // Send verification code via email or SMS
        if (email != null && !email.isEmpty()) {
            emailService.sendVerificationEmail(email, user.getVerificationCode());
        } else if (phoneNumber != null && !phoneNumber.isEmpty()) {
            // Send SMS
        }

        return user;
    }

    public boolean verifyUser(String verificationCode) {
        User user = userRepository.findByVerificationCode(verificationCode);
        if (user != null) {
            user.setVerified(true);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    public User completeProfile(Long userId, String fullName, String password) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setFullName(fullName);
            user.setPassword(password); // Encrypt the password in a real application
            return userRepository.save(user);
        }
        return null;
    }



    public boolean requestPasswordReset(String email) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setResetCode(generateVerificationCode());
            userRepository.save(user);
            // Send reset code to user's email
            if (email != null && !email.isEmpty()) {
                emailService.requestPasswordReset(email, user.getResetCode());
            }
            return true;
        }
        return false;
    }

    private String generateVerificationCode() {
        Random random = new Random();
        int otp = 1000 + random.nextInt(9000); // Ensures a 4-digit number
        return String.valueOf(otp);
    }


    public boolean verifyResetCode(String email, String resetCode) {
        Optional<User> userOpt = userRepository.findByEmailAndResetCode(email, resetCode);
        return userOpt.isPresent();
    }

    public boolean resetPassword(String email, String resetCode, String newPassword) {
        Optional<User> userOpt = userRepository.findByEmailAndResetCode(email, resetCode);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setPassword(newPassword);
            user.setResetCode(null);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    private String generateOtp() {
        Random random = new Random();
        int otp = 1000 + random.nextInt(9000); // Ensures a 4-digit number
        return String.valueOf(otp);
    }

}
