package com.projet.goodmood.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Tache {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idtache;
    private String designation;
    private Date date;
    private Boolean completed= false;

    @ManyToOne( optional = false)
    @JoinColumn(name = "users", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Users users;

    @ManyToOne
    @JoinColumn(name = "typetache", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private TypeTache typetache;

    @ManyToOne
    @JoinColumn(name = "priority", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Priority priority;

}
