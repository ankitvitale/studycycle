package com.studyCycle.StudyCycle.Repository;

import com.studyCycle.StudyCycle.entity.Donate;
import com.studyCycle.StudyCycle.entity.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DonationRepository extends JpaRepository<Donate, Long> {
    List<Donate> findAllByStatus(String status);
    @Query("SELECT s FROM Donate s WHERE s.product.prod_name LIKE %:match%")
    List<Donate> findMatching(@Param("match") String match);
}