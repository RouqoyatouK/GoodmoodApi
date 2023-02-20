package com.projet.goodmood.repository;

import com.projet.goodmood.models.Domaine;
import com.projet.goodmood.models.Planning;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface DomaineRepo extends JpaRepository<Domaine,Long> {

    Domaine findByNomdomaine(String nomdmn);
    Domaine findByIddomaine(Long iddomaine);

    //@Modifying


}
