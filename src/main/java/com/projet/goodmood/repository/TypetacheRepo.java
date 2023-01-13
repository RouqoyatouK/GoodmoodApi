package com.projet.goodmood.repository;

import com.projet.goodmood.models.TypeTache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypetacheRepo extends JpaRepository<TypeTache, Long> {

    TypeTache findByIdtypttache(Long idtypetache);
   // TypeTache findByNomtypetache();

    @Query(value = "select * from type_tache where idusers=:idusers ", nativeQuery = true)
    public List<TypeTache> AfficherTypeTacheDunUser(Long idusers);
}
