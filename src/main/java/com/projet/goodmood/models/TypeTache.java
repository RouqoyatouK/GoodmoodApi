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
public class TypeTache {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idtypttache;

    @Column(length = 50,  unique =true)
    private String nomtypetache;

    public String getNomtypetache() {
        return nomtypetache;
    }

    public void setNomtypetache(String nomtypetache) {
        this.nomtypetache = nomtypetache;
    }

    @ManyToOne( optional = false)
    @JoinColumn(name = "idusers", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Users users;

}
