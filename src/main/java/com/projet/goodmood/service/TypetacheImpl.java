package com.projet.goodmood.service;

import com.projet.goodmood.models.TypeTache;
import com.projet.goodmood.repository.TypetacheRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypetacheImpl implements TypetacheSvc{
    @Autowired
    TypetacheRepo typetacheRepo;
    @Override
    public TypeTache Creer(TypeTache typeTache) {
        return typetacheRepo.save(typeTache);
    }

   /* @Override
    public List<TypeTache> Aficher() {
        return typetacheRepo.findAll();
    }*/

    @Override
    public TypeTache Modifier(TypeTache typeTache, Long idtypetache) {
        return typetacheRepo.findById(idtypetache).map(t->{
            t.setNomtypetache(typeTache.getNomtypetache());
            t.setUsers(typeTache.getUsers());
            return typetacheRepo.save(t);
        }).orElseThrow(()->new RuntimeException("trouver!"));
    }

    @Override
    public String Supprimer(Long idtypetch) {
         this.typetacheRepo.deleteById(idtypetch);
        return"Ok";
    }
}
