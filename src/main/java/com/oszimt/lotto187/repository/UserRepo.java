package com.oszimt.lotto187.repository;

import com.oszimt.lotto187.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
