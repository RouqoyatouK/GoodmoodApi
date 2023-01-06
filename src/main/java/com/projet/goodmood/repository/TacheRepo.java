package com.projet.goodmood.repository;

import com.projet.goodmood.models.Tache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TacheRepo extends JpaRepository<Tache, Long> {
}
