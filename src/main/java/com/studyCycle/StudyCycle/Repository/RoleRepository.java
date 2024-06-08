package com.studyCycle.StudyCycle.Repository;

import com.studyCycle.StudyCycle.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository  extends CrudRepository<Role, String> {

  Role findByRoleName(String RoleName);

}
