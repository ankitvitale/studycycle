//package com.studyCycle.StudyCycle.Controller;
//
//import com.studyCycle.StudyCycle.Repository.CategoryRepository;
//import com.studyCycle.StudyCycle.Service.CategoryService;
//import com.studyCycle.StudyCycle.entity.Category;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.stereotype.Controller;
//
//import com.studyCycle.StudyCycle.Service.S3Service;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.util.List;
//
//@RestController
//public class CategoryController {
//
//    @Autowired
//    private CategoryService categoryService;
//
//    @Autowired
//    private CategoryRepository categoryRepository;
//    @PostMapping("/addNewCategory")
//
//    @PreAuthorize("hasRole('User')")
////    public void addNewCategory(@RequestParam String category) {
////        categoryService.createNewCategory(category);
////    }
//
//
//        public ResponseEntity<Category> addProduct(@RequestParam("category") String category,
//
//                                              @RequestParam("image") MultipartFile[] images) {
//       // String imageUrl = s3Service.uploadFile(image);
//        try {
//            // Upload files to S3 and get the URLs
//            S3Service s3Service=new S3Service();
//            List<String> imageUrls = s3Service.uploadFiles(images);
//
//            // Create new category
//            Category category = new Category();
//            category.setCategoryName(categoryName);
//            category.setImageUrls(imageUrls);
//
//            // Save category to the database
//            categoryRepository.save(category);
//
//            return ResponseEntity.ok(category);
//        } catch (IOException e) {
//            return ResponseEntity.status(500).body(null);
//        }
//    }
//    }
//
//
//
//}
