package com.studyCycle.StudyCycle.Controller;


import com.studyCycle.StudyCycle.Payload.SearchResponse;
import com.studyCycle.StudyCycle.Payload.VerificationRequest1;
import com.studyCycle.StudyCycle.Service.JwtService;
import com.studyCycle.StudyCycle.Service.UserService;
import com.studyCycle.StudyCycle.entity.JwtRequest;
import com.studyCycle.StudyCycle.entity.JwtResponse;
import com.studyCycle.StudyCycle.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController
//@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")

public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;
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

//    @PostMapping("/verify")
//    public String verify(@RequestParam String verificationCode) {
//        return userService.verifyUser(verificationCode);
//    }
//
@PostMapping("/verify")
public String verify(@RequestBody VerificationRequest1 verificationRequest) {
    return userService.verifyUser(verificationRequest.getVerificationCode());
}
//    @PostMapping("/complete-profile")
//    public User completeProfile( @RequestParam String phoneNumber,@RequestParam String email,@RequestParam String fullName, @RequestParam String password,@RequestParam String usertype) {
//        return userService.completeProfile(email,fullName, password,usertype,phoneNumber);
//    }

    @PostMapping("/complete-profile")
    public ResponseEntity<?> completeProfile(@RequestParam String phoneNumber,
                                             @RequestParam String email,
                                             @RequestParam String fullName,
                                             @RequestParam String password,
                                             @RequestParam String usertype) throws Exception {
        User user = userService.completeProfile(email, fullName, password, usertype, phoneNumber);
       // String token = jwtService.createJwtToken(new JwtRequest(email, password)).getToken(); // Create JWT token
        String token = jwtService.createJwtToken(new JwtRequest(email,password)).getJwtToken();
        // Return a response including the token
        return ResponseEntity.ok(new JwtResponse(user, token));
    }

    @PostMapping("/complete-admin")
    public User completeAdmin( @RequestParam String phoneNumber,@RequestParam String email,@RequestParam String fullName, @RequestParam String password,@RequestParam String usertype) {
        return userService.completeAdmin(email,fullName, password,usertype,phoneNumber);
    }


    @PostMapping("/claimMoney")
    @PreAuthorize("hasRole('User')")
    public void claimMoney(@RequestParam String upi_id, @RequestParam Double amount){
        userService.claimMoney(upi_id,amount);
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

//    @PostMapping("/banner")
//    public void Banner(@RequestParam )
}