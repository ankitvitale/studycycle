// package com.studyCycle.StudyCycle.Controller;


// import com.studyCycle.StudyCycle.Payload.SearchResponse;
// import com.studyCycle.StudyCycle.Payload.VerificationRequest1;
// import com.studyCycle.StudyCycle.Service.JwtService;
// import com.studyCycle.StudyCycle.Service.UserService;
// import com.studyCycle.StudyCycle.entity.JwtRequest;
// import com.studyCycle.StudyCycle.entity.JwtResponse;
// import com.studyCycle.StudyCycle.entity.User;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.access.prepost.PreAuthorize;
// import org.springframework.web.bind.annotation.*;

// import javax.annotation.PostConstruct;

// @RestController
// //@RequestMapping("/api/users")
// @CrossOrigin(origins = "*")

// public class UserController {

//     @Autowired
//     private UserService userService;

//     @Autowired
//     private JwtService jwtService;
//     @PostConstruct
//     public void initRoleAndUser() {
//         userService.initRoleAndUser();
//     }

//     @PostMapping("/register")
//     public User register(@RequestParam String email) {
//         if(email.contains("@")) {
//             return userService.registerUser(email);

//         }
//         return null;
//     }

// //    @PostMapping("/verify")
// //    public String verify(@RequestParam String verificationCode) {
// //        return userService.verifyUser(verificationCode);
// //    }
// //
// @PostMapping("/verify")
// public String verify(@RequestBody VerificationRequest1 verificationRequest) {
//     return userService.verifyUser(verificationRequest.getVerificationCode());
// }
// //    @PostMapping("/complete-profile")
// //    public User completeProfile( @RequestParam String phoneNumber,@RequestParam String email,@RequestParam String fullName, @RequestParam String password,@RequestParam String usertype) {
// //        return userService.completeProfile(email,fullName, password,usertype,phoneNumber);
// //    }

//     @PostMapping("/complete-profile")
//     public ResponseEntity<?> completeProfile(@RequestParam String phoneNumber,
//                                              @RequestParam String email,
//                                              @RequestParam String fullName,
//                                              @RequestParam String password,
//                                              @RequestParam String usertype) throws Exception {
//         User user = userService.completeProfile(email, fullName, password, usertype, phoneNumber);
//        // String token = jwtService.createJwtToken(new JwtRequest(email, password)).getToken(); // Create JWT token
//         String token = jwtService.createJwtToken(new JwtRequest(email,password)).getJwtToken();
//         // Return a response including the token
//         return ResponseEntity.ok(new JwtResponse(user, token));
//     }

//     @PostMapping("/complete-admin")
//     public User completeAdmin( @RequestParam String phoneNumber,@RequestParam String email,@RequestParam String fullName, @RequestParam String password,@RequestParam String usertype) {
//         return userService.completeAdmin(email,fullName, password,usertype,phoneNumber);
//     }


//     @PostMapping("/claimMoney")
//     @PreAuthorize("hasRole('User')")
//     public void claimMoney(@RequestParam String upi_id, @RequestParam Double amount){
//         userService.claimMoney(upi_id,amount);
//     }

//     @PostMapping("/request-password-reset")
//     public boolean requestPasswordReset(@RequestParam String email) {
//         return userService.requestPasswordReset(email);
//     }

//     @PostMapping("/verify-reset-code")
//     public boolean verifyResetCode(@RequestParam String email, @RequestParam String resetCode) {
//         return userService.verifyResetCode(email, resetCode);
//     }

//     @PostMapping("/reset-password")
//     public boolean resetPassword(@RequestParam String email, @RequestParam String resetCode, @RequestParam String newPassword) {
//         return userService.resetPassword(email, resetCode, newPassword);
//     }

//     // @GetMapping("/search")
//     // public SearchResponse search(@RequestParam String match ){
//     //    return userService.filter(match);
//     }

// //    @PostMapping("/banner")
// //    public void Banner(@RequestParam )
// }



package com.studyCycle.StudyCycle.Controller;

import com.studyCycle.StudyCycle.Payload.SearchResponse;
import com.studyCycle.StudyCycle.Payload.VerificationRequest1;
import com.studyCycle.StudyCycle.Service.JwtService;
import com.studyCycle.StudyCycle.Service.UserService;
import com.studyCycle.StudyCycle.entity.JwtRequest;
import com.studyCycle.StudyCycle.entity.JwtResponse;
import com.studyCycle.StudyCycle.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/api/users") // Base path for all user-related requests
@CrossOrigin(origins = "*")  // Allow cross-origin requests
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @PostConstruct
    public void initRoleAndUser() {
        userService.initRoleAndUser();
    }

    // Register a new user by email
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestParam String email) {
        if (email.contains("@")) {
            User user = userService.registerUser(email);
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid email format");
        }
    }

    // Verify the user using a verification code
    @PostMapping("/verify")
    public ResponseEntity<String> verify(@RequestBody VerificationRequest1 verificationRequest) {
        String result = userService.verifyUser(verificationRequest.getVerificationCode());
        if (result != null) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Verification failed");
        }
    }

    // Complete user profile
    @PostMapping("/complete-profile")
    public ResponseEntity<?> completeProfile(@RequestParam String phoneNumber,
                                             @RequestParam String email,
                                             @RequestParam String fullName,
                                             @RequestParam String password,
                                             @RequestParam String usertype) throws Exception {
        User user = userService.completeProfile(email, fullName, password, usertype, phoneNumber);
        String token = jwtService.createJwtToken(new JwtRequest(email, password)).getJwtToken();
        return ResponseEntity.ok(new JwtResponse(user, token));
    }

    // Complete admin profile
    @PostMapping("/complete-admin")
    public ResponseEntity<?> completeAdmin(@RequestParam String phoneNumber,
                                           @RequestParam String email,
                                           @RequestParam String fullName,
                                           @RequestParam String password,
                                           @RequestParam String usertype) {
        User user = userService.completeAdmin(email, fullName, password, usertype, phoneNumber);
        return ResponseEntity.ok(user);
    }

    // Claim money (only for users with the 'User' role)
    @PostMapping("/claimMoney")
    @PreAuthorize("hasRole('User')")
    public ResponseEntity<?> claimMoney(@RequestParam String upi_id, @RequestParam Double amount) {
        userService.claimMoney(upi_id, amount);
        return ResponseEntity.ok("Money claimed successfully");
    }

    // Request password reset
    @PostMapping("/request-password-reset")
    public ResponseEntity<?> requestPasswordReset(@RequestParam String email) {
        boolean result = userService.requestPasswordReset(email);
        if (result) {
            return ResponseEntity.ok("Password reset email sent");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Password reset request failed");
        }
    }

    // Verify password reset code
    @PostMapping("/verify-reset-code")
    public ResponseEntity<?> verifyResetCode(@RequestParam String email, @RequestParam String resetCode) {
        boolean result = userService.verifyResetCode(email, resetCode);
        if (result) {
            return ResponseEntity.ok("Reset code verified");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid reset code");
        }
    }

    // Reset password
    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestParam String email,
                                           @RequestParam String resetCode,
                                           @RequestParam String newPassword) {
        boolean result = userService.resetPassword(email, resetCode, newPassword);
        if (result) {
            return ResponseEntity.ok("Password reset successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Password reset failed");
        }
    }

    // Search users by a match criterion
    @GetMapping("/search")
    public ResponseEntity<SearchResponse> search(@RequestParam String match) {
        SearchResponse response = userService.filter(match);
        return ResponseEntity.ok(response);
    }

}
