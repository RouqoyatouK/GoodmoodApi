package com.projet.goodmood.controller;

import com.projet.goodmood.models.Priority;
import com.projet.goodmood.payload.response.MessageResponse;
import com.projet.goodmood.repository.PriorityRepo;
import com.projet.goodmood.service.PrioritySvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/priority")
public class PriorityCtrl {
    @Autowired
    private PrioritySvc prioritySvc;
    @Autowired
    PriorityRepo priorityRepo;

    @PostMapping("/add")
    public String Createe(@RequestBody Priority priority){
            this.prioritySvc.Creer(priority);
        return"ok";
    }

    @DeleteMapping("/delete/{idpriorite}")
    public String Supprimer(@PathVariable Long idpriorite){
        prioritySvc.Supprimer(idpriorite);
        return "succes";
    }

    @GetMapping("/read")
    public List<Priority> Afficher(){
        return prioritySvc.Afficher();
    }

    @PutMapping("/update/{idpriority}")
    public  Priority  Modifier( @PathVariable Long idpriority, @RequestBody Priority priority)
    {
        return prioritySvc.Modifier( priority, idpriority);
    }
}
