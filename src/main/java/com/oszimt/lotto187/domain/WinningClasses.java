package com.oszimt.lotto187.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity(name = "WinningClasses")
@Table(name = "winning_Classes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WinningClasses {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String winningClass;
    private int hits;
    private boolean isSuperHit;
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Tip> tips;
}
