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
public class Citation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idcitation ;
private String ecrivain;
private String contenu;
private String imagecitation;


    @ManyToOne( optional = false)
    @JoinColumn(name = "domaines", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Domaine domaine;

}
