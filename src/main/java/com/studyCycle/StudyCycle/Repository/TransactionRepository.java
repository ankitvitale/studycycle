package com.studyCycle.StudyCycle.Repository;

import com.studyCycle.StudyCycle.entity.Sell;
import com.studyCycle.StudyCycle.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
   // List<Transaction> findBySelld(Long sell_id);
    List<Transaction> findBySell(Sell sell);

    List<Transaction> findAllByOrderId(String orderId);
}
