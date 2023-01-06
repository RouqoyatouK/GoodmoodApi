package com.projet.goodmood.controller;


import com.projet.goodmood.models.Priority;
import com.projet.goodmood.models.Tache;
import com.projet.goodmood.models.TypeTache;
import com.projet.goodmood.models.Users;
import com.projet.goodmood.repository.PriorityRepo;
import com.projet.goodmood.repository.TacheRepo;
import com.projet.goodmood.repository.TypetacheRepo;
import com.projet.goodmood.repository.UsersRepo;
import com.projet.goodmood.service.TacheSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/tache")
public class TacheCtrl {

    @Autowired
    TacheSvc tacheSvc;
    @Autowired
    UsersRepo usersRepo;
    @Autowired
    PriorityRepo priorityRepo;
    @Autowired
    TypetacheRepo typetacheRepo;
    @Autowired
    TacheRepo tacheRepo;

    @PostMapping("/add/{idusers}/{idtypetache}/{idpriority}")
    public Tache Createe(@RequestBody Tache tache, @PathVariable Long idusers, @PathVariable Long idtypetache, @PathVariable Long idpriority ){

        Priority priority= priorityRepo.findByIdpriority(idpriority);
        TypeTache typeTache=typetacheRepo.findByIdtypttache(idtypetache);
        Users users= usersRepo.findById(idusers).get();

        tache.setTypetache(typeTache);
        tache.setPriority(priority);
        tache.setUsers(users);

        return tacheSvc.Ajouter(tache);
    }

    @PutMapping("/complet/{idtache}")
    public void TacheComplet(@PathVariable Long idtache){
        Tache tache= tacheRepo.findById(idtache).get();
        tache.setCompleted(!tache.getCompleted());
        tacheRepo.save(tache);
        System.out.println("jjjjjjjjjjjjjjjjjjjjjjjjjjjjjj:  "+  tache.getCompleted());
    }
}
