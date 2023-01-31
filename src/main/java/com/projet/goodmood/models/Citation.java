package com.projet.goodmood.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


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

    @JsonIgnore
    @ManyToOne( optional = false)
    @JoinColumn(name = "domaines", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Domaine domaine;


    @JsonIgnore
    @ManyToMany()
    @JoinTable(  name = "favoris_users",
            joinColumns = @JoinColumn(name = "favoris_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<Users> usersfav = new HashSet<>();

}
