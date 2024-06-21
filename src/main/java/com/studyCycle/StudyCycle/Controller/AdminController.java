package com.studyCycle.StudyCycle.Controller;

import com.studyCycle.StudyCycle.Service.AdminService;
import com.studyCycle.StudyCycle.entity.Category;
import com.studyCycle.StudyCycle.entity.Donate;
import com.studyCycle.StudyCycle.entity.Shopkeeper;
import com.studyCycle.StudyCycle.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/admin/")
@PreAuthorize("hasRole('Admin')")
public class AdminController {

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
    @PutMapping("/pushDueDate")
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

//    @PostMapping("/addNewCategory")
//    public void addNewCategory(@RequestParam String category) {
//        adminService.createNewCategory(category);
//    }
//
    @GetMapping("/category")
    public List<Category> getCategory() {
        return adminService.getCategory();
    }

    @GetMapping("/blockList")
    public List<User> getBlockList(){
        return adminService.getBlockList();
    }

}
