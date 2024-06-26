package com.studyCycle.StudyCycle.Repository;

import com.studyCycle.StudyCycle.entity.DonateHistry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonateHistryRepository extends JpaRepository<DonateHistry,Long> {
}
