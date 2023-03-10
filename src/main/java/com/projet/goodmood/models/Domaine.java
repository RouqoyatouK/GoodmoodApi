package com.projet.goodmood.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Domaine {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long iddomaine;
        private String nomdomaine;
        private String imagedomaine;
        private Date date ;

    @JsonIgnore
    @ManyToOne( optional = false)
        @JoinColumn(name = "users", nullable = false)
        private Users users;


    @JsonIgnore
        @ManyToMany()
        @JoinTable(  name = "domaine_user",
                joinColumns = @JoinColumn(name = "domaine_id"),
                inverseJoinColumns = @JoinColumn(name = "user_id"))
        private Set<Users> userss = new HashSet<>();






}
