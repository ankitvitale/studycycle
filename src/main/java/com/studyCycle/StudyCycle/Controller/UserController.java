package com.studyCycle.StudyCycle.Controller;


import com.studyCycle.StudyCycle.Payload.SearchResponse;
import com.studyCycle.StudyCycle.Service.UserService;
import com.studyCycle.StudyCycle.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController
//@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostConstruct
    public void initRoleAndUser() {
        userService.initRoleAndUser();
    }

    @PostMapping("/register")
    public User register(@RequestParam String email) {
        if(email.contains("@")) {
            return userService.registerUser(email);

        }
        return null;
    }

    @PostMapping("/verify")
    public String verify(@RequestParam String verificationCode) {
        return userService.verifyUser(verificationCode);
    }


    @PostMapping("/complete-profile")
    public User completeProfile( @RequestParam String phoneNumber,@RequestParam String email,@RequestParam String fullName, @RequestParam String password,@RequestParam String address,@RequestParam String usertype) {
        return userService.completeProfile(email,fullName, password,address,usertype,phoneNumber);
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

    @GetMapping("/search")
    public SearchResponse search(@RequestParam String match ){
       return userService.filter(match);
    }
    @PostMapping("/admin/block/{id}")
    public void blockUser(@PathVariable Long id) {
        userService.blockUser(id);
    }

    @PostMapping("/admin/unblock/{id}")
    public void unBlockUser(@PathVariable Long id) {
        userService.unBlockUser(id);
    }

    @PutMapping("/admin/pushDueDate")
    public void pushDueDate(@PathVariable Long id) {
        userService.pushDueDate(id);
    }
//    @PostMapping("/banner")
//    public void Banner(@RequestParam )
}