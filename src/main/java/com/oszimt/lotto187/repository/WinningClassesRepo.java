package com.oszimt.lotto187.repository;

import com.oszimt.lotto187.domain.WinningClasses;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WinningClassesRepo extends JpaRepository<WinningClasses, Long> {
    WinningClasses findByHitsAndIsSuperHit(int hits, boolean isSuperHit);

    List<WinningClasses> findByTips_Id(Long id);

    boolean existsByTips_Id(Long id);

}
