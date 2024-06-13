package com.studyCycle.StudyCycle.Repository;

import com.studyCycle.StudyCycle.entity.Shopkeeper;
import com.studyCycle.StudyCycle.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShopkeeperRepository extends CrudRepository<Shopkeeper, String> {
    Optional<Shopkeeper> findByUser(User user);
}
