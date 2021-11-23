package com.oszimt.lotto187.repository;

import com.oszimt.lotto187.domain.LottoNumbers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface LottoNumberRepo extends JpaRepository<LottoNumbers, Long> {
    @Query("select l from LottoNumbers l where l.drawingTime <= :tipTime order by l.drawingTime DESC")
    List<LottoNumbers> findFirstByOrderByDrawingTimeDesc(@Param("tipTime") LocalDateTime tipTime);
    /*
    @Query(value = "select l from Lotto_Numbers l where l.drawing_time <= :tipTime order by l.drawing_time DESC LIMIT 1", nativeQuery = true)
    LottoNumbers findFirstDrawingTimeDesc(@Param("tipTime") LocalDateTime tipTime);
    */
}
