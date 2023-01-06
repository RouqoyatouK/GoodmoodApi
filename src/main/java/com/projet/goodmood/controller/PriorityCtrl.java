package com.projet.goodmood.controller;

import com.projet.goodmood.models.Priority;
import com.projet.goodmood.models.TypeTache;
import com.projet.goodmood.service.PrioritySvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/priority")
public class PriorityCtrl {
    @Autowired
    private PrioritySvc prioritySvc;

    @PostMapping("/add")
    public Priority Createe(@RequestBody Priority priority){
        return prioritySvc.Creer(priority);
    }
}
