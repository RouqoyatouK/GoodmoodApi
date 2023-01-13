package com.projet.goodmood.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.projet.goodmood.img.SaveImage;
import com.projet.goodmood.models.Citation;
import com.projet.goodmood.models.Domaine;
import com.projet.goodmood.repository.CitationRepo;
import com.projet.goodmood.repository.DomaineRepo;
import com.projet.goodmood.security.ResponseMessage;
import com.projet.goodmood.service.CitationSrv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/citation")
public class CitationCtrl {
    @Autowired
    CitationSrv citationSrv;
    @Autowired
    CitationRepo citationRepo;

    @Autowired
    DomaineRepo domaineRepo;


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

        if (find == null) {
            if (file != null) {
                ctt.setImagecitation(SaveImage.save("domaines", file, ctt.getEcrivain()));
                ctt.setDomaine(dom);
                citationSrv.Creer(ctt);
                System.out.println("jjjjjjjjjjjjggg"+ ctt.getDomaine().getNomdomaine());
                return ResponseMessage.generateResponse("ok", HttpStatus.OK, " citation enregistré !");

            }else {
                return ResponseMessage.generateResponse("error", HttpStatus.BAD_REQUEST, "Fichier vide");

            }
        }else {
            return ResponseMessage.generateResponse("error", HttpStatus.BAD_GATEWAY, "Une citation existe déja avec le même nom !");

        }
    }



}