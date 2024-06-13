package com.studyCycle.StudyCycle.Service;


import com.studyCycle.StudyCycle.Repository.CategoryRepository;
import com.studyCycle.StudyCycle.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    public Category createNewCategory(String category) {
        return categoryRepository.save(new Category(category));
    }

    public List<Category> getCategory(){
        return categoryRepository.findAll();
    }
}
