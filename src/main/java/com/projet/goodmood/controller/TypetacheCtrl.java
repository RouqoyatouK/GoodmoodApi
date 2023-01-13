package com.projet.goodmood.controller;


import com.projet.goodmood.models.TypeTache;
import com.projet.goodmood.models.Users;
import com.projet.goodmood.repository.TypetacheRepo;
import com.projet.goodmood.repository.UsersRepo;
import com.projet.goodmood.service.TypetacheSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/typetache")
public class TypetacheCtrl {
    @Autowired
    private TypetacheSvc typetacheSvc;
    @Autowired
    UsersRepo usersRepo;
    @Autowired
    TypetacheRepo typetacheRepo;




    @PostMapping("/add/{idusers}")
    public TypeTache Createe(@RequestBody TypeTache typeTache, @PathVariable Long idusers){
        Users users= usersRepo.findById(idusers).get();
        typeTache.setUsers(users);
        return typetacheSvc.Creer(typeTache);
    }

    @GetMapping("/read/{idusers}")
    public List<TypeTache> Readd(@PathVariable Long idusers){
        Users users= usersRepo.findById(idusers).get();
        return typetacheRepo.AfficherTypeTacheDunUser(idusers);
    }

    @DeleteMapping("/delete/{idusers}/{idtypetache}")
    public String Suppression(@PathVariable Long idusers, @PathVariable Long idtypetache){
        Users users= usersRepo.findById(idusers).get();
      // TypeTache typeTache= typetacheRepo.findByNomtypetache();
        TypeTache typeTache = typetacheRepo.findByIdtypttache(idtypetache);
        if (typeTache.getUsers().equals(users)){
            typetacheSvc.Supprimer(idtypetache);
            return "Supprimer avec succes";}
        else return "vous n'avez pas ce droit";
    }

    @PutMapping("/update/{idusers}/{idtypetache}")
    public String Modification(@PathVariable Long idusers, @PathVariable Long idtypetache, @RequestBody TypeTache typeTache){
        Users users= usersRepo.findById(idusers).get();
        TypeTache typeTach = typetacheRepo.findByIdtypttache(idtypetache);
        //if (typeTache.getUsers().equals(users)){
            //this.typetacheSvc.Modifier(typeTache,idtypetache);
            this.typetacheSvc.Modifier(typeTache, idtypetache);
          return   "Modifier avec succes";

        /*} else
        return "vous navez pas ce droit";*/

        }
}
