package com.projet.goodmood.service;

import com.projet.goodmood.models.Planning;
import com.projet.goodmood.repository.PlanningRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanningImpl implements PlanningSvc {
    @Autowired
    PlanningRepo planningRepo;

    @Override
    public Planning Ajouter(Planning planning) {
        return planningRepo.save(planning);
    }

    @Override
    public List<Planning> Afficher() {
        return planningRepo.findAll();
    }

    @Override
    public Planning Modifier(Planning planning, Long idplanning) {
        return planningRepo.findById(idplanning).map(p->{
            p.setNomplanning(planning.getNomplanning());
            p.setUsers(planning.getUsers());
            p.setDatedebut(planning.getDatedebut());
            p.setDatefin(planning.getDatefin());
            return planningRepo.save(planning);
        }).orElseThrow(()->new RuntimeException("trouver!"));
    }

    @Override
    public String Supprimer(Long idplanning) {
        this.planningRepo.deleteById(idplanning);
        return "ok!!";
    }
}
