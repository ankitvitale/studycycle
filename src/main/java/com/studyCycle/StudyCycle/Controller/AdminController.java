package com.studyCycle.StudyCycle.Controller;

import com.studyCycle.StudyCycle.Payload.CLaimMoney;
import com.studyCycle.StudyCycle.Repository.CategoryRepository;
import com.studyCycle.StudyCycle.Service.AdminService;
import com.studyCycle.StudyCycle.Service.CategoryService;
import com.studyCycle.StudyCycle.entity.Category;
import com.studyCycle.StudyCycle.entity.Shopkeeper;
import com.studyCycle.StudyCycle.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController("/admin/")
@PreAuthorize("hasRole('Admin')")
public class AdminController {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private AdminService adminService;
    @GetMapping("/user")
    public List<User> getUsers() {
       return adminService.getUsers("Individual");
    }

    @GetMapping("/shopkeepers")
    public List<User> getShopkeepers() {
        return adminService.getUsers("Shopkeeper");
    }

    @GetMapping("/DueList")
    public List<Shopkeeper> dueList() {
       return adminService.dueList();
    }
    @PutMapping("/pushDueDate/{id}")
    public void pushDueDate(@PathVariable Long id) {
        adminService.pushDueDate(id);
    }

    @PostMapping("/block/{id}")
    public void blockUser(@PathVariable Long id) {
        adminService.blockUser(id);
    }

    @PostMapping("/unblock/{id}")
    public void unBlockUser(@PathVariable Long id) {
        adminService.unBlockUser(id);
    }

    @GetMapping("/claimMoneyList")
    public List<CLaimMoney> claimMoney(){
       return adminService.claimMoney();
    }

    @PostMapping("/markClaimed")
    public void markClaimed(@RequestParam("userId") Long userId,
                            @RequestParam("claimedMoney") Double claimedMoney){
        adminService.markClaimed(userId,claimedMoney);
    }

//    @PostMapping("/addNewCategory")
//    @PreAuthorize("hasRole('User')")
//    public ResponseEntity<Category> addCategory(@RequestParam("category") String categoryName,
//                                                @RequestParam("image") MultipartFile image) {
//        try {
//            String imageUrl = categoryService.uploadFile(image);
//            Category category = new Category();
//            category.setCategoryName(categoryName);
//            category.setImageUrl(imageUrl);
//            categoryRepository.save(category);
//            return ResponseEntity.ok(category);
//        } catch (IOException e) {
//            return ResponseEntity.status(500).body(null);
//        }
//    }
    @GetMapping("/category")
    public List<Category> getCategory() {
        return adminService.getCategory();
    }

    @GetMapping("/blockList")
    public List<User> getBlockList(){
        return adminService.getBlockList();
    }

}
