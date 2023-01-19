package com.projet.goodmood.service;

import com.projet.goodmood.models.Planning;

import java.util.List;

public interface PlanningSvc {
    Planning Ajouter (Planning planning);
    List<Planning> Afficher();
    Planning Modifier (Planning planning, Long idplanning);
    String Supprimer (Long idplanning);
}
