package com.projet.goodmood.service;

import com.projet.goodmood.models.Domaine;
import com.projet.goodmood.models.Users;
import com.projet.goodmood.repository.DomaineRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DomaineImpl implements DomaineSvc{
    @Autowired
    DomaineRepo domaineRepo;
    @Override
    public Domaine Creer(Domaine domaine) {
        return domaineRepo.save(domaine);
    }

    @Override
    public List<Domaine> Afficher() {
        return domaineRepo.findAll();
    }

    @Override
    public Domaine Modifier(Domaine domaine, Long iddomaine) {
        return domaineRepo.findById(iddomaine).map(d->{
            d.setNomdomaine(domaine.getNomdomaine());
            d.setImagedomaine(domaine.getImagedomaine());
            d.setDate(domaine.getDate());
            return domaineRepo.save(d);
        }).orElseThrow(()-> new RuntimeException("domaine trouver"));
    }

    @Override
    public String Supprimer(Long iddomaine) {
         this.domaineRepo.deleteById(iddomaine);
         return "ok";
    }

    @Override
    public Domaine AjouterDomainePourUser(Domaine domaine) {
        return domaineRepo.save(domaine);
    }


}
