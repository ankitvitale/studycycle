package com.studyCycle.StudyCycle.Repository;

import com.studyCycle.StudyCycle.entity.Sell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SellRepository extends JpaRepository<Sell, Long> {
    @Query("SELECT s FROM Sell s WHERE s.product.prod_name LIKE %:match%")
    List<Sell> findMatching(@Param("match") String match);
}