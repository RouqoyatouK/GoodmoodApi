package com.projet.goodmood.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Favoris {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idfavoris;

    @ManyToOne( optional = false)
    @JoinColumn(name = "citations", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Citation citation;
}
