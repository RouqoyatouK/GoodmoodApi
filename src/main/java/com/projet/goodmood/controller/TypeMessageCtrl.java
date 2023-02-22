package com.projet.goodmood.controller;


import com.projet.goodmood.models.Planning;
import com.projet.goodmood.models.Priority;
import com.projet.goodmood.models.Typemessage;
import com.projet.goodmood.models.Users;
import com.projet.goodmood.payload.response.MessageResponse;
import com.projet.goodmood.repository.TypemessageRepo;
import com.projet.goodmood.repository.UsersRepo;
import com.projet.goodmood.service.TypemessageSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
@RestController
@RequestMapping("/typemessage")
public class TypeMessageCtrl {
    @Autowired
    TypemessageSvc typemessageSvc;
    @Autowired
    UsersRepo usersRepo;

    @Autowired
    TypemessageRepo typemessageRepo;

    @PostMapping("/add/{idadmin}")
    public ResponseEntity<?> Createe(@RequestBody Typemessage typemessage, @PathVariable Long idadmin) {
        Users users = usersRepo.findById(idadmin).get();
        String nomtypemessage = typemessage.getNomtypemessage();
        Typemessage typemessage1 = typemessageRepo.findByNomtypemessage(nomtypemessage);

        if (typemessage.getNomtypemessage() != ""){
            if (typemessage1==null){
                typemessage.setAdmin(users);
                this.typemessageSvc.Creer(typemessage);
                return ResponseEntity.ok().body(new MessageResponse("typemessage ajouter avec succes"));

            }else {
                return ResponseEntity.ok().body(new MessageResponse("Ce type message existte déjà"));

            }

        }else {
            return ResponseEntity.ok().body(new MessageResponse("Le nom du type message est obligatoire"));

        }
    }
    @GetMapping("/read")
    public List<Typemessage> Lireeeee() {
       return  typemessageSvc.Afficher();
    }


    @PutMapping("/update/{idtypemessage}")
    public  Typemessage Modifierr(@PathVariable Long idtypemessage, @RequestBody Typemessage typemessage){

        Typemessage typemessage1= typemessageRepo.findById(idtypemessage).get();
        return typemessageSvc.Modifier(typemessage, idtypemessage);
    }

    @DeleteMapping("/delete/{idtypemessage}")
    public  String Supprimer(@PathVariable Long idtypemessage){
        typemessageSvc.Supprimer(idtypemessage);
        return "succes";
    }

    }
