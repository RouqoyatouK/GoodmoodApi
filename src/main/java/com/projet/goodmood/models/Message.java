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
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idmessage;
    private String message;

    @ManyToOne
    @JoinColumn(name = "usersmessage", nullable = false)
    private Users usersmessage;

    @ManyToOne
    @JoinColumn(name = "typemassage", nullable = false)
    private Typemessage typemassage;

}
