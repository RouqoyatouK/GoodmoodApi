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
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    //faire en sorte que le type du enum soir string
    @Enumerated(EnumType.STRING)
    private ERole name;

    public Role(ERole role) {
        this.name = role;
    }



    }
