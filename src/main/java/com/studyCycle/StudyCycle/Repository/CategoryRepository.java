package com.studyCycle.StudyCycle.Repository;

import com.studyCycle.StudyCycle.entity.Category;
import com.studyCycle.StudyCycle.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByCategoryName(String category);
}