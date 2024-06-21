package com.studyCycle.StudyCycle.Repository;

import com.studyCycle.StudyCycle.entity.DonateCart;
import com.studyCycle.StudyCycle.entity.RentCart;
import com.studyCycle.StudyCycle.entity.SellCart;
import com.studyCycle.StudyCycle.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentCartRepository extends JpaRepository<RentCart,Integer> {
    public List<Integer> findByEmail(String email);

    List<RentCart> findByEmail(User email);
}
