package com.projet.goodmood.repository;

import com.projet.goodmood.models.Planning;
import com.projet.goodmood.models.Tache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanningRepo extends JpaRepository<Planning, Long> {

    @Query(value = "select * from planning where users=:users ", nativeQuery = true)
    public List<Planning>  AfficherPlanningDunUser(Long users);

    Planning findByNomplanning(String nomplanning);
    Planning findByNomplanningAndUsers(String nomplanning, Long users);



}
