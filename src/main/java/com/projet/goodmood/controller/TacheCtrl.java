package com.projet.goodmood.controller;


import com.projet.goodmood.models.*;
import com.projet.goodmood.repository.*;
import com.projet.goodmood.service.TacheSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:8100", maxAge = 3600, allowCredentials = "true")
@RestController
@RequestMapping("/tache")
public class TacheCtrl {

    @Autowired
    TacheSvc tacheSvc;
    @Autowired
    PlanningRepo planningRepo;
    @Autowired
    PriorityRepo priorityRepo;
    @Autowired
    TypetacheRepo typetacheRepo;
    @Autowired
    TacheRepo tacheRepo;

    @PostMapping("/add/{idplanning}/{idtypetache}/{idpriority}")
    public Tache Createe(@RequestBody Tache tache, @PathVariable Long idplanning, @PathVariable Long idtypetache, @PathVariable Long idpriority ){

        Priority priority= priorityRepo.findByIdpriority(idpriority);
        TypeTache typeTache=typetacheRepo.findByIdtypttache(idtypetache);
        Planning planning= planningRepo.findById(idplanning).get();

        tache.setTypetache(typeTache);
        tache.setPriority(priority);
        tache.setPlanning(planning);

        return tacheSvc.Ajouter(tache);
    }

    @PutMapping("/complet/{idtache}")
    public String Valider(@PathVariable Long idtache){
        Tache tache=tacheRepo.findById(idtache).get();
        //tache.setCompleted(!tache.getCompleted());
        tache.setCompleted(Boolean.TRUE);
        tacheRepo.save(tache);
        return "ok";
    }



    @DeleteMapping("/delete/{idtache}")
    public String Supprimer(@PathVariable Long idtache, @PathVariable Long idplanning){
        Tache tache=tacheRepo.findById(idtache).get();
        tacheSvc.Spprimer(idtache);
        return "succes";

    }

    @GetMapping("/read/{idplanning}")
    public List<Tache> Readd(@PathVariable Long idplanning){
        Planning planning= planningRepo.findById(idplanning).get();
        return tacheRepo.AfficherTacheDunUser(idplanning);
    }

    @PutMapping("/update/{idtache}")
    public String Modifier ( @PathVariable Long idtache, @RequestBody Tache tache){
        Tache tach =tacheRepo.findById(idtache).get();
        tacheSvc.Modifier(tache, idtache);
        return"ok!";

        }
}
