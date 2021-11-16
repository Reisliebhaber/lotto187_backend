package com.oszimt.lotto187.repository;

import com.oszimt.lotto187.domain.Payout;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PayoutRepo extends JpaRepository<Payout, Long> {
    boolean existsByTips_Id(Long id);

    List<Payout> findByTips_Id(Long id);
}
