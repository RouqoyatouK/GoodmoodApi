package com.projet.goodmood.service;

import com.projet.goodmood.models.Domaine;
import com.projet.goodmood.models.Users;

import java.util.List;

public interface DomaineSvc {

    Domaine Creer (Domaine domaine);
    List<Domaine> Afficher();
    Domaine Modifier(Domaine domaine, Long iddomaine);
    String Supprimer(Long iddomaine);

    //user
Domaine AjouterDomainePourUser(Domaine domaine);

}
