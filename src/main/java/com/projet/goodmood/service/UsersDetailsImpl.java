package com.projet.goodmood.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projet.goodmood.models.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UsersDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;


    private Long id;

    private String username;

    private String email;

    @JsonIgnore
    private String password;

    //GrantedAuthority est le privilège individuel . Exemples:  READ_AUTHORITY , WRITE_PRIVILEGE ou même CAN_EXECUTE_AS_ROOT

    private Collection<? extends GrantedAuthority> authorities;

    public UsersDetailsImpl(Long id, String username, String email, String password,
                                    Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    //return l'user avec tous ces droits et toutes ces informations
    public static UsersDetailsImpl build(Users user) {

        /*
         * Dans Spring Security, nous pouvons considérer chaque GrantedAuthority comme un privilège individuel.
         *  Les exemples peuvent inclure READ_AUTHORITY , WRITE_PRIVILEGE
         * De même, dans Spring Security, nous pouvons considérer chaque rôle comme une GrantedAuthority à
         * gros grains représentée sous la forme d'une chaîne et préfixée par « ROLE »
         * */

        //Stream est utilisée pour traiter des collections d'objets
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());

        //on cree retourne un objet CollaborateurDetailsImpl
        return new UsersDetailsImpl(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                authorities
        );
    }

    //recupere les information l'user de l'utilisateur
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UsersDetailsImpl user = (UsersDetailsImpl) o;
        return Objects.equals(id, user.id);
    }
}
