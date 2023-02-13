package com.projet.goodmood.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Typemessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idtypemessage;
    private String nomtypemessage;

    @ManyToOne
    @JoinColumn(name = "admin", nullable = false)
    private Users admin;

}
