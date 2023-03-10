package com.projet.goodmood.service;

import com.projet.goodmood.models.Tache;
import org.springframework.stereotype.Service;

import java.lang.reflect.Modifier;
import java.util.List;


public interface TacheSvc {
    Tache Ajouter (Tache tache);
    List<Tache> Afficher();
    Tache Modifier(Tache tache, Long idtache);

    Tache Modifiercomplet(Tache tache, Long idtache);

    String Spprimer (Long idtache);
}
