package com.projet.goodmood.controller;


import com.projet.goodmood.models.*;
import com.projet.goodmood.repository.*;
import com.projet.goodmood.service.TacheSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
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
    public String Createe(@RequestBody Tache tache, @PathVariable Long idplanning, @PathVariable Long idtypetache, @PathVariable Long idpriority
    ){

        Priority priority= priorityRepo.findByIdpriority(idpriority);
        TypeTache typeTache=typetacheRepo.findByIdtypttache(idtypetache);
        Planning planning= planningRepo.findById(idplanning).get();

        Date date1= planning.getDatedebut();
        Date date2 = planning.getDatefin();
        Date datetache= tache.getDate();
      //  System.out.println(date2 + "kkkkkkkkkk" +date1);

        if (tache.getDate().after(planning.getDatedebut()) && tache.getDate().before(planning.getDatefin())){
            System.out.println(planning.getDatedebut() + "Kalaban" +planning.getDatefin());
            System.out.println(date2 + "kkkkkkkkkk" +date1);
            tache.setTypetache(typeTache);
            tache.setPriority(priority);
            tache.setPlanning(planning);

            this.tacheSvc.Ajouter(tache);
            return "tache creer avec succes";


        }else {

            return "la datetache doit etre entre date debut et fin de planning";
        }
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
