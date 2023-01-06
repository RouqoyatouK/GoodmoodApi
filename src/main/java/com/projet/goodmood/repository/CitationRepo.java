package com.projet.goodmood.repository;

import com.projet.goodmood.models.Citation;
import com.projet.goodmood.models.Domaine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitationRepo extends JpaRepository<Citation, Long> {
    Citation findByEcrivain(String ecrivain);


}
