package com.projet.goodmood.controller;

import com.projet.goodmood.models.Planning;
import com.projet.goodmood.models.Users;
import com.projet.goodmood.repository.PlanningRepo;
import com.projet.goodmood.repository.UsersRepo;
import com.projet.goodmood.service.PlanningSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8100", maxAge = 3600, allowCredentials = "true")
@RequestMapping("/planning")
public class PlanningCtrl {
    @Autowired
    PlanningSvc planningSvc;
    @Autowired
    UsersRepo usersRepo;
    @Autowired
    PlanningRepo planningRepo;

    @PostMapping("/add/{idusers}")
    public Planning Ajouterrr(@RequestBody Planning planning, @PathVariable Long idusers){
        Users users = usersRepo.findById(idusers).get();
        planning.setUsers(users);
        planning.setDate(new Date());
        return planningSvc.Ajouter(planning);
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
