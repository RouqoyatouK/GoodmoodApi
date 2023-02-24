package com.projet.goodmood.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.projet.goodmood.img.SaveImage;
import com.projet.goodmood.models.Citation;
import com.projet.goodmood.models.Domaine;
import com.projet.goodmood.models.Users;
import com.projet.goodmood.repository.CitationRepo;
import com.projet.goodmood.repository.DomaineRepo;
import com.projet.goodmood.repository.UsersRepo;
import com.projet.goodmood.security.ResponseMessage;
import com.projet.goodmood.service.CitationSrv;
import com.projet.goodmood.service.NotificationSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
@RequestMapping("/citation")
public class CitationCtrl {
    @Autowired
    CitationSrv citationSrv;
    @Autowired
    CitationRepo citationRepo;

    @Autowired
    DomaineRepo domaineRepo;
    @Autowired
    UsersRepo usersRepo;
    @Autowired
    NotificationSvc notificationSvc;


    @PostMapping("/add/{iddomaine}")
    public ResponseEntity<Object> Create(@RequestParam(value = "citation") String citations,
                                         @PathVariable Long iddomaine,
                                         @Param(value = "file") MultipartFile file) throws JsonProcessingException {

/*(value = "domaine") comprend le vrai nom bien ecrist c'est lui qu'on ecrit dans postman au niveau du json
alors que String domainees c'est une variable de plus au niveau de  dmn.setImagedomaine(SaveImage.save("domaines".. ci dessous
le domaines ecrit ici doit etre identique a celui ecrit au niveau type dans la classe saveImage */
        Domaine dom= domaineRepo.findByIddomaine(iddomaine);
        Citation ctt= new JsonMapper().readValue(citations, Citation.class);
        Citation find = citationRepo.findByEcrivain(ctt.getEcrivain());



                ctt.setImagecitation(SaveImage.save("domaines", file, ctt.getEcrivain()));
                ctt.setDomaine(dom);
                citationSrv.Creer(ctt);
                System.out.println("jjjjjjjjjjjjggg"+ ctt.getDomaine().getNomdomaine());
                notificationSvc.generateNotificationByType(citationSrv.Creer(ctt),dom);
                return ResponseMessage.generateResponse("ok", HttpStatus.OK, " citation enregistré !");



    }

//afficher les citation d'un domaine

   @GetMapping("/read/{idusers}")
    public List<Citation> Afficher (@PathVariable Long idusers){
       // Citation citation= citationRepo.f
        //Domaine dmn= domaineRepo.findByIddomaine(iddomaine);
        Users users= usersRepo.findById(idusers).get();
       return citationRepo.Affichertoulescitationdundomain(idusers);
    }


    //Ajouter favoris pour users
    @PostMapping("/addfavoris/{idcitation}/{idusers}")
    public ResponseEntity<Object> AjouterFavorisPourUsers(@PathVariable Long idcitation, @PathVariable Long idusers){
        Citation citation= citationRepo.findById(idcitation).get();
        Users users= usersRepo.findById(idusers).get();

        citation.getUsersfav().add(users);
        citationRepo.save(citation);
        return ResponseMessage.generateResponse("ok", HttpStatus.OK, " favoris enregistré !");

    }

    //Afficher les favoris dun user
    @GetMapping("/readfav/{idusers}")
    public  Object Affhicher(@PathVariable Long idusers){
        Set<Citation> users=  usersRepo.findById(idusers).get().getCitation();
        //return citationRepo.AffichertoulesFavoisdunUsers(idusers);
        return  users;
    }

    //Supprimer favoris

    @DeleteMapping("/deletefav/{idcitation}/{idusers}")
    public ResponseEntity<?> SupprimerFavorisDunUsers(@PathVariable Long idcitation, @PathVariable Long idusers){
        Citation citation= citationRepo.findById(idcitation).get();
        citation.getUsersfav().remove(usersRepo.findById(idusers).get());
        citationRepo.save(citation);
        return ResponseMessage.generateResponse("ok", HttpStatus.OK, " favoris supprimé !");
    }



}
