package com.studyCycle.StudyCycle.Repository;

import com.studyCycle.StudyCycle.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ProductRepository extends JpaRepository<Product, Long> {

}
