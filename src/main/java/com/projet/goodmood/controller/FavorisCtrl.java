package com.projet.goodmood.controller;

import com.projet.goodmood.models.Citation;
import com.projet.goodmood.models.Favoris;
import com.projet.goodmood.repository.CitationRepo;
import com.projet.goodmood.repository.FavorisRepo;
import com.projet.goodmood.service.FavorisSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/favoris")
public class FavorisCtrl {
    @Autowired
    private FavorisSvc favorisSvc;
    @Autowired
    FavorisRepo favorisRepo;
    @Autowired
    CitationRepo citationRepo;

    /*@PostMapping("/add/{idcitation}")
    public Favoris Create(@PathVariable Long idcitation){
       //Citation ctin= citationRepo.findByEcrivain().getIdcitation(idcitation);
      return favorisRepo.save();
    }*/

}
