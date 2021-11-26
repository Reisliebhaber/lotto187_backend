package com.oszimt.lotto187.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity(name = "Tip")
@Table(name = "tips")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tip {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String tips;
    private int superNumber;
    private LocalDateTime tippingTime;
    private double payout;
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<User> users;
}
