package com.projet.goodmood.service;

import com.projet.goodmood.models.Priority;
import com.projet.goodmood.models.Typemessage;

import java.util.List;

public interface TypemessageSvc {

    Typemessage Creer(Typemessage typemessage);
    List<Typemessage> Afficher();
    Typemessage Modifier(Typemessage typemessage, Long idtypemessage);
    String Supprimer(Long idtypemessage);
}

