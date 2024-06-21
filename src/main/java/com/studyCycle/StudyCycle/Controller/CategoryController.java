package com.studyCycle.StudyCycle.Controller;

import com.studyCycle.StudyCycle.Repository.CategoryRepository;
import com.studyCycle.StudyCycle.Service.CategoryService;
import com.studyCycle.StudyCycle.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.io.IOException;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    //    @Autowired
//    private S3Service s3Service;
    @Autowired
    private CategoryRepository categoryRepository;

    @PostMapping("/addNewCategory")

    @PreAuthorize("hasRole('User')")
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
}

