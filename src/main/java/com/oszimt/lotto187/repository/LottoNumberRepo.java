package com.oszimt.lotto187.repository;

import com.oszimt.lotto187.domain.LottoNumbers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LottoNumberRepo extends JpaRepository<LottoNumbers, Long> {
}
