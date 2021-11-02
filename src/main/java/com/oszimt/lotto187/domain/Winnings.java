package com.oszimt.lotto187.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity(name = "Winnings")
@Table(name = "winnings")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Winnings {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String tips;
    private int superNumber;
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<User> users;
}
