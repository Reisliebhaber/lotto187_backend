package com.oszimt.lotto187.repository;

import com.oszimt.lotto187.domain.Tip;
import com.oszimt.lotto187.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TipRepo extends JpaRepository<Tip, Long> {
    List<Tip> findAllByUsers(User user);
}
