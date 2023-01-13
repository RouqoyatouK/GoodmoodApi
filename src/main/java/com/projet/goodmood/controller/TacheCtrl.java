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

import java.util.List;

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

    @DeleteMapping("/delete/{idusers}/{idtache}")
    public String Supprimer(@PathVariable Long idtache, @PathVariable Long idusers){
        Users users= usersRepo.findById(idusers).get();
        Tache tache=tacheRepo.findById(idtache).get();
        if (tache.getUsers().equals(users)){
        tacheSvc.Spprimer(idtache);
        return "succes";
        }else return "vous n'avez pas ce droit";
    }

    @GetMapping("/read/{idusers}")
    public List<Tache> Readd(@PathVariable Long idusers){
        Users users= usersRepo.findById(idusers).get();
        return tacheRepo.AfficherTacheDunUser(idusers);
    }

    @PutMapping("/update/{idusers}/{idtache}")
    public String Modifier (@PathVariable Long idusers, @PathVariable Long idtache, @RequestBody Tache tache){
        Users users= usersRepo.findById(idusers).get();
        Tache tach =tacheRepo.findById(idtache).get();

        if (tach.getUsers().equals(users)){
            tacheSvc.Modifier(tache, idtache);
            return"succes";
        }else {
            return "pas le droit";
        }

        }
}
