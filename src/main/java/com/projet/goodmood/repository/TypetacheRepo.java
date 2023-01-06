package com.projet.goodmood.repository;

import com.projet.goodmood.models.TypeTache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypetacheRepo extends JpaRepository<TypeTache, Long> {

    TypeTache findByIdtypttache(Long idtypetache);
}
