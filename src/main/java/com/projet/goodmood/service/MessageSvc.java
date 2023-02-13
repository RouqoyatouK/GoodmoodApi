package com.projet.goodmood.service;


import com.projet.goodmood.models.Message;
import com.projet.goodmood.models.Typemessage;

import java.util.List;

public interface MessageSvc {
    Message Creer(Message message);
    List<Message> Afficher();
    Message Modifier(Message message, Long idmessage);
    String Supprimer(Long idmessage);
}
