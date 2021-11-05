package com.oszimt.lotto187.repository;

import com.oszimt.lotto187.domain.Winnings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WinningsRepo extends JpaRepository<Winnings, Long> {
    //List<Winnings> findAllByUsers(User user);
}
