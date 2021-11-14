package com.oszimt.lotto187.repository;

import com.oszimt.lotto187.domain.Payout;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PayoutRepo extends JpaRepository<Payout, Long> {
}
