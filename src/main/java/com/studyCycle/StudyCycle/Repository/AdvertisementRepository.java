package com.studyCycle.StudyCycle.Repository;

import com.studyCycle.StudyCycle.entity.Advertisement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvertisementRepository extends CrudRepository<Advertisement, String> {
}
