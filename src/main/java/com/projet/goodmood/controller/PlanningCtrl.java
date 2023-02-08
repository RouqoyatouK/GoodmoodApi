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
        if(planning.getDatedebut().before(planning.getDatefin())  || planning.getDatedebut().equals(planning.getDatefin())) {

            planning.setUsers(users);
             this.planningSvc.Ajouter(planning);
            return ResponseEntity.ok().body(new MessageResponse("planning ajouter avec succes"));

        }
        else {


            return ResponseEntity.ok().body(new MessageResponse("la date de debut dois etre inferieur a la date de fin")) ;
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
}
