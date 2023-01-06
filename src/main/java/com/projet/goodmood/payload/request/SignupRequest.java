package com.projet.goodmood.payload.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;


@Getter
@Setter
public class SignupRequest {

    //cette classe est utiliser lorsqu'on veut creer un compte elle contient les attributs necessaires
    //pour cela mais ce n'est pas une table

    private String username;

    private String email;

    private Set<String> role;

    private String password;

}
