package com.studyCycle.StudyCycle.Repository;

import com.studyCycle.StudyCycle.entity.Address;
import com.studyCycle.StudyCycle.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository  extends JpaRepository<Address,Long> {

    List<Address> findAllByUser(User user);
}
