package com.projet.goodmood.repository;


import com.projet.goodmood.models.Message;
import com.projet.goodmood.models.Typemessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepo extends JpaRepository<Message, Long> {

    Message findByCotenumessage(String cotenumessage );

}
