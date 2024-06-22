package com.studyCycle.StudyCycle.Repository;

import com.studyCycle.StudyCycle.Payload.Sellinput;
import com.studyCycle.StudyCycle.entity.Sell;
import com.studyCycle.StudyCycle.entity.SellCart;
import com.studyCycle.StudyCycle.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SellRepository extends JpaRepository<Sell, Long> {
    @Query("SELECT s FROM Sell s WHERE s.type = 'New' AND s.product.prod_name LIKE %:match%" )
    List<Sell> findNewMatching(@Param("match") String match);

    @Query("SELECT s FROM Sell s WHERE s.type= :sellType")
    List<Sell> findAllByType(@Param("sellType") String sellType);

    @Query("SELECT s FROM Sell s WHERE s.type = 'Used' AND s.product.prod_name LIKE %:match%" )
    List<Sell> findOldMatching(@Param("match") String match);


    // public List<Integer> findByEmail(String email);


}