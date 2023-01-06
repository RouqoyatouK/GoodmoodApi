package com.projet.goodmood.service;


import com.projet.goodmood.models.Citation;

import java.util.List;

public interface CitationSrv {

    Citation Creer (Citation citation);
    List<Citation> Afficher();
    Citation Modifier(Citation citation, Long idcitation);
    String Supprimer(Long idcitation);
}
