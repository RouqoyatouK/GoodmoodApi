package com.projet.goodmood.service;


import com.projet.goodmood.models.Citation;
import com.projet.goodmood.repository.CitationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CitationImpl implements CitationSrv {
    @Autowired
    CitationRepo citationRepo;

    @Override
    public Citation Creer(Citation citation) {
        return citationRepo.save(citation);
    }

    @Override
    public List<Citation> Afficher() {
        return citationRepo.findAll();
    }

    @Override
    public Citation Modifier(Citation citation, Long idcitation) {
        return citationRepo.findById(idcitation).map(c->{
            c.setContenu(citation.getContenu());
            c.setEcrivain(citation.getEcrivain());
            c.setImagecitation(citation.getImagecitation());
            c.setDomaine(citation.getDomaine());
            return citationRepo.save(c);
        }).orElseThrow(()->new RuntimeException("trouver!"));
    }

    @Override
    public String Supprimer(Long idcitation) {
         citationRepo.deleteById(idcitation);
        return"ok";
    }
}
