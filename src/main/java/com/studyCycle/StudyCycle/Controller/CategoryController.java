package com.studyCycle.StudyCycle.Controller;

import com.studyCycle.StudyCycle.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    @PostMapping("/addNewCategory")
    public void addNewCategory(@RequestParam String category) {
        categoryService.createNewCategory(category);
    }

}
