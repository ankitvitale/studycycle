package com.studyCycle.StudyCycle.Repository;

import com.studyCycle.StudyCycle.entity.Shopkeeper;
import com.studyCycle.StudyCycle.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ShopkeeperRepository extends CrudRepository<Shopkeeper, String> {
    Optional<Shopkeeper> findByUser(User user);
    @Query("SELECT s FROM Shopkeeper s WHERE s.due_date BETWEEN CURRENT_DATE AND :endDate")
    List<Shopkeeper> findShopkeepersWithDueDateWithinNext7Days(@Param("endDate") Date endDate);

}
