package com.studyCycle.StudyCycle.Controller;


import com.studyCycle.StudyCycle.Service.JwtService;
import com.studyCycle.StudyCycle.Service.UserService;
import com.studyCycle.StudyCycle.entity.JwtRequest;
import com.studyCycle.StudyCycle.entity.JwtResponse;
import com.studyCycle.StudyCycle.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")


//@RequestMapping("/api/users")
public class JwtController {


    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;


    @PostMapping({"/login"})
    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        return jwtService.createJwtToken(jwtRequest);
    }


    @PostMapping({"/registerAdmin"})
    public User registerNewUser(@RequestBody User user) {
        return userService.registerAdmin(user);
    }
}
