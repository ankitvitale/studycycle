package com.studyCycle.StudyCycle.Repository;

import com.studyCycle.StudyCycle.entity.DonateCart;
import com.studyCycle.StudyCycle.entity.SellCart;
import com.studyCycle.StudyCycle.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<SellCart ,Integer> {
    public List<Integer> findByEmail(String email);

  //  List<SellCart> findByUser(User email);

    List<SellCart> findByEmail(User email);
}
