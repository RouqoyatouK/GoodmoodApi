package com.projet.goodmood.repository;

import com.projet.goodmood.models.TypeTache;
import com.projet.goodmood.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface UsersRepo extends JpaRepository<Users, Long> {

    Optional<Users> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);

    @Query(value = "select * from users, role, user_roles where role.name = 'admin'  ", nativeQuery = true)
    public List<Object> AfficherToutLesUser();

}
