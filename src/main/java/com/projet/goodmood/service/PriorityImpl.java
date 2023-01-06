package com.projet.goodmood.service;

import com.projet.goodmood.models.Priority;
import com.projet.goodmood.repository.PriorityRepo;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriorityImpl implements PrioritySvc {
    @Autowired
    PriorityRepo priorityRepo;
    @Override
    public Priority Creer(Priority priority) {
        return priorityRepo.save(priority);
    }

    @Override
    public List<Priority> Afficher() {
        return priorityRepo.findAll();
    }

    @Override
    public Priority Modifier(Priority priority, Long idpriority) {
        return priorityRepo.findById(idpriority).map(p->{
            p.setNompriority(priority.getNompriority());
            return priorityRepo.save(p);
        }).orElseThrow(()-> new RuntimeException("okkkkkkkk trouver!"));
    }

    @Override
    public String Supprimer(Long idpriority) {
         this.priorityRepo.deleteById(idpriority);
        return"ok!";
    }
}
