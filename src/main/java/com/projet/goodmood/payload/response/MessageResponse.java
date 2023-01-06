package com.projet.goodmood.payload.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MessageResponse {
    /*on utilisera cette classe pour gerer les differents  messages a l'utilisateur avec
    seulement l'attribut message
   */

    private String message;

}
