package com.projet.goodmood.service;

import com.projet.goodmood.models.Tache;
import com.projet.goodmood.repository.TacheRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TacheImpl implements TacheSvc{
    @Autowired
    TacheRepo tacheRepo;
    @Override
    public Tache Ajouter(Tache tache) {
        return tacheRepo.save(tache);
    }

    @Override
    public List<Tache> Afficher() {
        return tacheRepo.findAll();
    }

    @Override
    public Tache Modifier(Tache tache, Long idtache) {
        return tacheRepo.findById(idtache).map(t->{
            t.setDesignation(tache.getDesignation());
            t.setCompleted(tache.getCompleted());
            t.setDate(tache.getDate());
            t.setPriority(tache.getPriority());
            t.setTypetache(tache.getTypetache());
            t.setPlanning(tache.getPlanning());
            return tacheRepo.save(t);
        }).orElseThrow(()->new RuntimeException("trouver"));
    }

    @Override
    public Tache Modifiercomplet(Tache tache, Long idtache) {
        return tacheRepo.findById(idtache).map(tc -> {
            tc.setCompleted(tache.getCompleted());
            return tacheRepo.save(tc);
        }).orElseThrow(() -> new RuntimeException("trouver"));
    }

    @Override
    public String Spprimer(Long idtache) {
        this.tacheRepo.deleteById(idtache);
        return "ok!";
    }
}
