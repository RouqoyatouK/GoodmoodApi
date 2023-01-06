package com.projet.goodmood.service;

import com.projet.goodmood.models.TypeTache;

import java.util.List;

public interface TypetacheSvc {

    TypeTache Creer(TypeTache typeTache);
    List<TypeTache> Aficher();
    TypeTache Modifier(TypeTache typeTache, Long idtypetache);
    String Supprimer(Long idtypetch);
}
