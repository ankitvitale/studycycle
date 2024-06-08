package com.studyCycle.StudyCycle.Repository;

import com.studyCycle.StudyCycle.entity.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RentRepository extends JpaRepository<Rent, Long> {

    List<Rent> findAllByStatus(String status);
    @Query("SELECT s FROM Rent s WHERE s.product.prod_name LIKE %:match%")
    List<Rent> findMatching(@Param("match") String match);
}