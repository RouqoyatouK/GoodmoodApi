package com.projet.goodmood.models;

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
public class Domaine {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long iddomaine;
        private String nomdomaine;
        private String imagedomaine;


        @ManyToOne( optional = false)
        @JoinColumn(name = "users", nullable = false)
        @OnDelete(action = OnDeleteAction.CASCADE)
        private Users users;


     /*   @ManyToMany()
        @JoinTable(  name = "domaine_user",
                joinColumns = @JoinColumn(name = "domaine_id"),
                inverseJoinColumns = @JoinColumn(name = " user_id"))
        private Set<Users> users=new HashSet<>();


        public Domaine(Set<Users> users) {
                this.users = users;
        }

        public Set<Users> getUsers() {
                return users;
        }

        public void setUsers(Set<Users> users) {
                this.users = users;
        }*/
}
