package com.studyCycle.StudyCycle.Service;


import com.studyCycle.StudyCycle.Repository.CategoryRepository;
import com.studyCycle.StudyCycle.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    public void createNewCategory(String category) {
        categoryRepository.save(new Category(category));
    }
}
