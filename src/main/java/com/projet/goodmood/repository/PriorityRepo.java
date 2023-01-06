package com.projet.goodmood.repository;

import com.projet.goodmood.models.Priority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriorityRepo extends JpaRepository<Priority, Long> {

    Priority findByIdpriority(Long idpriority);
}
