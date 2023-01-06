package com.projet.goodmood.repository;

import com.projet.goodmood.models.ERole;
import com.projet.goodmood.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
    Role findByName(ERole name);
}
