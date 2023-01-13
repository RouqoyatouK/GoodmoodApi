package com.projet.goodmood.repository;

import com.projet.goodmood.models.Tache;
import com.projet.goodmood.models.TypeTache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TacheRepo extends JpaRepository<Tache, Long> {

    @Query(value = "select * from tache where users=:users ", nativeQuery = true)
    public List<Tache> AfficherTacheDunUser(Long users);


}
