package com.oszimt.lotto187.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity(name = "Payout")
@Table(name = "payout")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payout {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer payout;
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Tip> tips;
}
