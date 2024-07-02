package com.studyCycle.StudyCycle.Repository;

import com.studyCycle.StudyCycle.entity.Product;
import com.studyCycle.StudyCycle.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {

}
