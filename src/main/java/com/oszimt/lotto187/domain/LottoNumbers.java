package com.oszimt.lotto187.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity(name = "LottoNumbers")
@Table(name = "Lotto_Numbers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LottoNumbers{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String lottoNumbers;
    private int superNumber;
    private LocalDateTime drawingTime;
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Tip> tips;
}
