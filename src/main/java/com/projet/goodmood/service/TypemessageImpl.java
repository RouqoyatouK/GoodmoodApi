package com.projet.goodmood.service;


import com.projet.goodmood.models.Typemessage;
import com.projet.goodmood.repository.TypemessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypemessageImpl implements TypemessageSvc {

    @Autowired
    TypemessageRepo typemessageRepo;
    @Override
    public Typemessage Creer(Typemessage typemessage) {
        return typemessageRepo.save(typemessage) ;
    }

    @Override
    public List<Typemessage> Afficher() {
        return typemessageRepo.findAll();
    }

    @Override
    public Typemessage Modifier(Typemessage typemessage, Long idtypemessage) {
        return typemessageRepo.findById(idtypemessage).map(tm->{
            tm.setNomtypemessage(typemessage.getNomtypemessage());
            return typemessageRepo.save(tm);
        }).orElseThrow(()-> new RuntimeException("okkkkkkkk trouver!"));
    }

    @Override
    public String Supprimer(Long idtypemessage) {
        this.typemessageRepo.deleteById(idtypemessage);
        return "ok!";
    }
}
