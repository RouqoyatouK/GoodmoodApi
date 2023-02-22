package com.projet.goodmood.repository;


import com.projet.goodmood.models.Planning;
import com.projet.goodmood.models.Typemessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypemessageRepo extends JpaRepository<Typemessage, Long> {
    Typemessage findByNomtypemessage(String nomtypemessage );

}
