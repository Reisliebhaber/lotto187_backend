package com.oszimt.lotto187.repository;

import com.oszimt.lotto187.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Long> {
    Role findByName(String name);//TODO depends on Role attribute "name"
}
