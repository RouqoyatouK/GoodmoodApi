package com.projet.goodmood.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.projet.goodmood.img.SaveImage;
import com.projet.goodmood.models.Domaine;
import com.projet.goodmood.models.Users;
import com.projet.goodmood.repository.DomaineRepo;
import com.projet.goodmood.repository.UsersRepo;
import com.projet.goodmood.security.ResponseMessage;
import com.projet.goodmood.service.DomaineSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
@RequestMapping("/domaine")
public class DomaineCtrl {
    @Autowired
    DomaineRepo domaineRepo;

    @Autowired
    private DomaineSvc domaineSvc;
    @Autowired
    UsersRepo usersRepo;

    @PostMapping("/add/{id}")
    public ResponseEntity<Object> Create(@RequestParam(value = "domaine") String domainees,
                                         @PathVariable Long id,
                                         @Param(value = "file") MultipartFile file) throws JsonProcessingException{

/*(value = "domaine") comprend le vrai nom bien ecrist c'est lui qu'on ecrit dans postman au niveau du json
alors que String domainees c'est une variable de plus au niveau de  dmn.setImagedomaine(SaveImage.save("domaines".. ci dessous
le domaines ecrit ici doit etre identique a celui ecrit au niveau type dans la classe saveImage */
        Users usr= usersRepo.findById(id).get();
        Domaine dmn= new JsonMapper().readValue(domainees, Domaine.class);
        Domaine find = domaineRepo.findByNomdomaine(dmn.getNomdomaine());
        if (find == null) {
            if (file != null) {
               dmn.setImagedomaine(SaveImage.save("domaines", file, dmn.getNomdomaine()));
                   dmn.setUsers(usr);



               domaineSvc.Creer(dmn);
                System.out.println("hhhhhhhhhhhhhhh"+dmn.getImagedomaine());
                return ResponseMessage.generateResponse("ok", HttpStatus.OK, " domaine enregistré !");

            }else {
                return ResponseMessage.generateResponse("error", HttpStatus.BAD_REQUEST, "Fichier vide");

            }
        }else {
            return ResponseMessage.generateResponse("error", HttpStatus.BAD_GATEWAY, "Une region existe déja avec le même nom !");

        }
    }
}

