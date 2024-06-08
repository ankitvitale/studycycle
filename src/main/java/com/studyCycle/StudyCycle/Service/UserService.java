package com.studyCycle.StudyCycle.Service;

import com.studyCycle.StudyCycle.Payload.SearchResponse;
import com.studyCycle.StudyCycle.Repository.RoleRepository;
import com.studyCycle.StudyCycle.Repository.UserRepository;
import com.studyCycle.StudyCycle.entity.Role;
import com.studyCycle.StudyCycle.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserRepository userDao;
    @Autowired
    private RoleRepository roleDao;
    @Autowired
    private EmailService emailService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RentService rentService;
    @Autowired
    private SellService sellService;
    @Autowired
    private DonationService donationService;


   // HashMap<String, String> verifyOtp = new HashMap<>();

    public void initRoleAndUser() {
        Role adminRole = new Role();
        adminRole.setRoleName("Admin");
        adminRole.setRoleDescription("Admin role");
        if (!roleDao.existsById("Admin")) {
            roleDao.save(adminRole);
        }

        Role userRole = new Role();
        userRole.setRoleName("User");
        userRole.setRoleDescription("Default role for newly created record");
        roleDao.save(userRole);
    }

    public User registerUser(String email) {

        User user = new User();
        user.setEmail(email);
        user.setVerificationCode(generateOtp());
        user.setVerified(false);
        userDao.save(user);

        // Send verification code via email or SMS
        if (email != null && !email.isEmpty()) {
            emailService.sendVerificationEmail(email, user.getVerificationCode());
        }

        return user;
    }

    public User registerAdmin(User user) {
        if (user.getPassword() == null) {
            throw new IllegalArgumentException("Password cannot be null");
        }

        System.out.println("Registering admin with email: " + user.getEmail());
        System.out.println("Raw password: " + user.getPassword());

        Role role = roleDao.findById("Admin").get();
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(role);
        user.setRole(userRoles);
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Encode the password
        return userDao.save(user);
    }

    public String verifyUser(String verificationCode) {
        User user = userDao.findByVerificationCode(verificationCode);
        if (user != null) {
            user.setVerified(true);
            userDao.save(user);
            return user.getEmail();
        }
        return null;
    }

    public User completeProfile(String email, String fullName, String password) {
        if (password == null) {
            throw new IllegalArgumentException("Password cannot be null");
        }

        User user = userDao.findByEmail(email);
        if (user != null) {
            user.setFullName(fullName);
            user.setPassword(passwordEncoder.encode(password)); // Encode the password
            Role role = roleDao.findById("User").get();
            Set<Role> userRoles = new HashSet<>();
            userRoles.add(role);
            user.setRole(userRoles);
            return userDao.save(user);
        }
        return null;
    }

    public boolean requestPasswordReset(String email) {

        User user = userDao.findByEmail(email);
        if (user != null) {

            user.setResetCode(generateVerificationCode());
            userDao.save(user);
            // Send reset code to user's email
            if (email != null && !email.isEmpty()) {
                emailService.requestPasswordReset(email, user.getResetCode());
            }
            return true;
        }
        return false;
    }

    public boolean verifyResetCode(String email, String resetCode) {
        Optional <User> userOpt = userDao.findByEmailAndResetCode(email, resetCode);
        return userOpt.isPresent();
    }
    //
    public boolean resetPassword(String email, String resetCode, String newPassword) {
        Optional<User> userOpt = userDao.findByEmailAndResetCode(email, resetCode);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setPassword(newPassword);
            user.setResetCode(null);
            userDao.save(user);
            return true;
        }
        return false;
    }

    private String generateVerificationCode() {
        Random random = new Random();
        int otp = 1000 + random.nextInt(9000); // Ensures a 4-digit number
        return String.valueOf(otp);
    }

    private String generateOtp() {
        Random random = new Random();
        int otp = 1000 + random.nextInt(9000); // Ensures a 4-digit number
        return String.valueOf(otp);
    }

    public String getEncodedPassword(String password) {
        if (password == null) {
            throw new IllegalArgumentException("Password cannot be null");
        }
        System.out.println("Encoding password: " + password);
        return passwordEncoder.encode(password);
    }

    public User findUser(String currentUser) {
       return userDao.findByEmail(currentUser);
    }


    public SearchResponse filter(String match) {
       return new SearchResponse(rentService.findMatching(match),sellService.findMatching(match),donationService.findMatching(match));
    }
}
