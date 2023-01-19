package com.projet.goodmood.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Planning {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idplanning;

    @Column(length = 50,  unique =true)
    private String nomplanning;
    private Date date;

    @ManyToOne
    @JoinColumn(name = "users", nullable = false)
    private Users users;

}
