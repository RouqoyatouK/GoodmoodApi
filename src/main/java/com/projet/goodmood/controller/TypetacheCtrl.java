package com.projet.goodmood.controller;


import com.projet.goodmood.models.TypeTache;
import com.projet.goodmood.service.TypetacheSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/typetache")
public class TypetacheCtrl {
    @Autowired
    private TypetacheSvc typetacheSvc;

    @PostMapping("/add")
    public TypeTache Createe(@RequestBody TypeTache typeTache){
        return typetacheSvc.Creer(typeTache);
    }

    @GetMapping("/read")
    public List<TypeTache> Readd(){
        return typetacheSvc.Aficher();
    }
}
