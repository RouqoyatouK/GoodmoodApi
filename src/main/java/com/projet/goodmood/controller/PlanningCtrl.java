package com.projet.goodmood.controller;

import com.projet.goodmood.models.Planning;
import com.projet.goodmood.models.Users;
import com.projet.goodmood.payload.response.MessageResponse;
import com.projet.goodmood.repository.PlanningRepo;
import com.projet.goodmood.repository.UsersRepo;
import com.projet.goodmood.service.PlanningSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
@RequestMapping("/planning")
public class PlanningCtrl {
    @Autowired
    PlanningSvc planningSvc;
    @Autowired
    UsersRepo usersRepo;
    @Autowired
    PlanningRepo planningRepo;



    @PostMapping("/add/{idusers}")
    public ResponseEntity<?> Ajouterrr(@RequestBody Planning planning, @PathVariable Long idusers){
        Users users = usersRepo.findById(idusers).get();
        String nomplanning = planning.getNomplanning();
        Long isuser = planning.getUsers().getId();
        Planning planning1= planningRepo.findByNomplanningAndUsers(nomplanning, isuser);

        Date d = new Date();
        if (planning.getNomplanning() != "" ) {
         //   return ResponseEntity.ok().body(new MessageResponse(" ce planning existe déjà"));
           if (planning1 == null){
               if (planning.getDatedebut().before(planning.getDatefin())  || planning.getDatedebut().equals(planning.getDatefin())) {
                   if (planning.getDatedebut().equals(d) || planning.getDatedebut().after(d) && planning.getDatefin().equals(d) || planning.getDatefin().after(d)) {
                       planning.setUsers(users);
                       this.planningSvc.Ajouter(planning);
                       return ResponseEntity.ok().body(new MessageResponse("planning ajouter avec succes"));
                   }
                   else {
                       return ResponseEntity.ok().body(new MessageResponse(" Les date doivent être superieur ou égale à la date d'aujourdhui"));
                   }

               }  else {
                   return ResponseEntity.ok().body(new MessageResponse(" La date de debut doit être inferieur à la date de fin"));
               }

           }else {
               return ResponseEntity.ok().body(new MessageResponse("Ce nom planning existe dejà"));

           }
        }
        else {
           // return ResponseEntity.ok().body(new MessageResponse(" ce planning debut infe à suppe"));
            return ResponseEntity.ok().body(new MessageResponse("Le nom planning est vide "));
        }




    }

    @GetMapping("/read/{idusers}")
    public List<Planning>  Lireeeee(@PathVariable Long idusers) {
        Users users = usersRepo.findById(idusers).get();
        return planningRepo.AfficherPlanningDunUser(idusers);
    }

    @PutMapping("/update/{idplanning}")
    public  Planning Modifierr(@PathVariable Long idplanning, @RequestBody Planning planning){
        Planning planning1= planningRepo.findById(idplanning).get();
        return planningSvc.Modifier(planning, idplanning);
    }

    @DeleteMapping("/delete/{idplanning}")
    public  ResponseEntity<?> Supprimer(@PathVariable Long idplanning){
        this.planningSvc.Supprimer(idplanning);
        return ResponseEntity.ok().body(new MessageResponse("planning supprimer avec succes"));

    }


    }
