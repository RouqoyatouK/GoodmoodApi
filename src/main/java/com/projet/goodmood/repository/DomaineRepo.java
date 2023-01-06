package com.projet.goodmood.repository;

import com.projet.goodmood.models.Domaine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DomaineRepo extends JpaRepository<Domaine,Long> {

    Domaine findByNomdomaine(String nomdmn);
    Domaine findByIddomaine(Long iddomaine);
}
