package com.projet.goodmood.service;

import com.projet.goodmood.models.Domaine;
import com.projet.goodmood.models.Favoris;

import java.util.List;

public interface FavorisSvc {
    Favoris Creer (Favoris favoris);
    List<Favoris> Afficher();
    Favoris Modifier(Favoris favoris, Long idfavoris);
    String Supprimer(Long idfavoris);
}
