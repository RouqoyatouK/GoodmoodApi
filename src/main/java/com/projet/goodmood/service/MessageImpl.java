package com.projet.goodmood.service;

import com.projet.goodmood.models.Message;
import com.projet.goodmood.repository.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageImpl implements MessageSvc {
    @Autowired
    MessageRepo messageRepo;

    @Override
    public Message Creer(Message message) {
        return messageRepo.save(message);
    }

    @Override
    public List<Message> Afficher() {
        return messageRepo.findAll();
    }

    @Override
    public Message Modifier(Message message, Long idmessage) {
        return messageRepo.findById(idmessage).map(m->{
            m.setMessage(message.getMessage());
           return messageRepo.save(message);
        }).orElseThrow(()-> new RuntimeException("kkkkkkkkkkkkkkkkkkk"));
    }

    @Override
    public String Supprimer(Long idmessage) {
        this.messageRepo.deleteById(idmessage);
        return "ok!";
    }
}
