package com.studyCycle.StudyCycle.Controller;

import com.studyCycle.StudyCycle.Configuration.JwtRequestFilter;
import com.studyCycle.StudyCycle.Repository.AdvertisementRepository;
import com.studyCycle.StudyCycle.Repository.CategoryRepository;
import com.studyCycle.StudyCycle.Repository.UserRepository;
import com.studyCycle.StudyCycle.Service.CategoryService;
import com.studyCycle.StudyCycle.entity.Advertisement;
import com.studyCycle.StudyCycle.entity.Category;
import com.studyCycle.StudyCycle.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.S3Client;

import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.io.IOException;

@RestController
public class CategoryController {

    private final String bucketName = "studycycle";
    private final String spaceUrl = "https://studycycle.blr1.digitaloceanspaces.com/";
    private final S3Client s3Client;

    public CategoryController(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private AdvertisementRepository advertisementRepository;





    @PostMapping("/addNewCategory")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<Category> addCategory(@RequestParam("category") String categoryName,
                                                @RequestParam("image") MultipartFile image) {
        try {
            String imageUrl = categoryService.uploadFile(image);
            Category category = new Category();
            category.setCategoryName(categoryName);
            category.setImageUrl(imageUrl);
            categoryRepository.save(category);
            return ResponseEntity.ok(category);
        } catch (IOException e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/getAllCategory")
    @PreAuthorize("hasRole('User')")
    public List<Category> getAllCategory() {
              return categoryService.getAllCategory();
    }

//    @PostMapping("/addNewbanner")
//    @PreAuthorize("hasRole('User')")
//    public ResponseEntity<Advertisement> addAdvertisement(@RequestParam("banner") MultipartFile image) {
//        try {
//            String imageUrl = categoryService.uploadFile(image);
//            Advertisement advertisement = new Advertisement();
//
//            advertisement.setBanner(imageUrl);
//            String user1 = JwtRequestFilter.CURRENT_USER;
//            advertisement.setUser(user);
//            advertisementRepository.save(advertisement);
//
//            return ResponseEntity.ok(advertisement);
//        } catch (IOException e) {
//            return ResponseEntity.status(500).body(null);
//        }


@PostMapping("/addNewbanner")
@PreAuthorize("hasRole('User')")
public ResponseEntity<Advertisement> addAdvertisement(@RequestParam("banner") MultipartFile image) {
    try {
        String imageUrl = categoryService.uploadFile(image);
        Advertisement advertisement = new Advertisement();
        advertisement.setBanner(imageUrl);

        // Get the current user from the JWT request filter
        String email = JwtRequestFilter.CURRENT_USER;

        // Fetch the user from the database
        User currentUser = userRepository.findByEmail(email);

        if (currentUser == null) {
            return ResponseEntity.status(404).body(null); // User not found
        }

        // Set the user to the advertisement
        advertisement.setUser(currentUser);

        // Save the advertisement
        advertisementRepository.save(advertisement);

        return ResponseEntity.ok(advertisement);
    } catch (IOException e) {
        return ResponseEntity.status(500).body(null);
    }

}
    }

