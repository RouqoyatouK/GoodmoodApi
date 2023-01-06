package com.projet.goodmood.repository;

import com.projet.goodmood.models.Favoris;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavorisRepo extends JpaRepository<Favoris, Long> {
}
