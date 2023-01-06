package com.projet.goodmood.service;

import com.projet.goodmood.models.Favoris;
import com.projet.goodmood.repository.FavorisRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavorisImpl implements FavorisSvc{
    @Autowired
    FavorisRepo favorisRepo;
    @Override
    public Favoris Creer(Favoris favoris) {
        return favorisRepo.save(favoris);
    }

    @Override
    public List<Favoris> Afficher() {
        return favorisRepo.findAll();
    }

    @Override
    public Favoris Modifier(Favoris favoris, Long idfavoris) {
        return favorisRepo.findById(idfavoris).map(f->{
            f.setCitation(favoris.getCitation());
            return favorisRepo.save(f);
        }).orElseThrow(()->new RuntimeException("Favoris trouver"));
    }

    @Override
    public String Supprimer(Long idfavoris) {
         this.favorisRepo.deleteById(idfavoris);
        return "Ok!";
    }
}
