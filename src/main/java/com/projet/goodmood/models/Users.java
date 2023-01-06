package com.projet.goodmood.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //Not blank veut dire que ça doit pas etre num

    private  String username;
    private String email;
    private String password;

    @ManyToMany()
    @JoinTable(  name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();


   /* @ManyToMany(mappedBy="users")
    private Set<Domaine> domaines=new HashSet<>();*/


    public Users(String username, String email, String encode) {
        this.username = username;
        this.email = email;
        this.password = encode;

    }



}
