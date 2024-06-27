package com.studyCycle.StudyCycle.Repository;

import com.studyCycle.StudyCycle.entity.RentHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentHistoryRepository extends JpaRepository<RentHistory, Long> {

}
