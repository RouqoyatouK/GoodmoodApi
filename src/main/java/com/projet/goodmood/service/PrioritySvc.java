package com.projet.goodmood.service;

import com.projet.goodmood.models.Priority;

import java.util.List;

public interface PrioritySvc {
    Priority Creer(Priority priority);
    List<Priority> Afficher();
    Priority Modifier(Priority priority, Long idpriority);
    String Supprimer(Long idpriority);
}
