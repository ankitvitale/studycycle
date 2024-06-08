package com.studyCycle.StudyCycle.Service;

import com.studyCycle.StudyCycle.Repository.RoleRepository;
import com.studyCycle.StudyCycle.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleDao;

    public Role createNewRole(Role role) {
        return roleDao.save(role);
    }
}
