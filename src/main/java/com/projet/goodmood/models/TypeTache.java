package com.projet.goodmood.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
public class TypeTache {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idtypttache;
    @Column(unique = true)
    private String Nomtypetache;

    public TypeTache(Long idtypttache, String nomtypetache) {
        this.idtypttache = idtypttache;
        Nomtypetache = nomtypetache;
    }

    public TypeTache() {
    }

    public Long getIdtypttache() {
        return idtypttache;
    }

    public void setIdtypttache(Long idtypttache) {
        this.idtypttache = idtypttache;
    }

    public String getNomtypetache() {
        return Nomtypetache;
    }

    public void setNomtypetache(String nomtypetache) {
        Nomtypetache = nomtypetache;
    }
}
