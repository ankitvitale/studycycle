package com.studyCycle.StudyCycle.Controller;


import com.studyCycle.StudyCycle.Payload.PasswordResetRequest;
import com.studyCycle.StudyCycle.Payload.UpdatePasswordRequest;
import com.studyCycle.StudyCycle.Payload.VerifyResetCodeRequest;
import com.studyCycle.StudyCycle.Service.UserService;
import com.studyCycle.StudyCycle.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User register(@RequestParam String email, @RequestParam String phoneNumber) {
        return userService.registerUser(email, phoneNumber);
    }

    @PostMapping("/verify")
    public boolean verify(@RequestParam String verificationCode) {
        return userService.verifyUser(verificationCode);
    }

    @PostMapping("/complete-profile")
    public User completeProfile(@RequestParam Long userId, @RequestParam String fullName, @RequestParam String password) {
        return userService.completeProfile(userId, fullName, password);
    }

    @PostMapping("/request-password-reset")
    public boolean requestPasswordReset(@RequestParam String email) {
        return userService.requestPasswordReset(email);
    }

    @PostMapping("/verify-reset-code")
    public boolean verifyResetCode(@RequestParam String email, @RequestParam String resetCode) {
        return userService.verifyResetCode(email, resetCode);
    }

    @PostMapping("/reset-password")
    public boolean resetPassword(@RequestParam String email, @RequestParam String resetCode, @RequestParam String newPassword) {
        return userService.resetPassword(email, resetCode, newPassword);
    }
}