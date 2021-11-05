package com.oszimt.lotto187.repository;

import com.oszimt.lotto187.domain.WinningClasses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WinningClassesRepo extends JpaRepository<WinningClasses, Long> {
    WinningClasses findByHitsAndIsSuperHit(int hits, boolean isSuperHit);
}
