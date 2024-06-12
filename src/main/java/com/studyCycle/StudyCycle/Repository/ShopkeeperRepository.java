package com.studyCycle.StudyCycle.Repository;

import com.studyCycle.StudyCycle.entity.Shopkeeper;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopkeeperRepository extends CrudRepository<Shopkeeper, String> {
}
