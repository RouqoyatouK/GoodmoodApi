package com.projet.goodmood.controller;

import com.projet.goodmood.models.Message;
import com.projet.goodmood.models.Typemessage;
import com.projet.goodmood.models.Users;
import com.projet.goodmood.payload.response.MessageResponse;
import com.projet.goodmood.repository.MessageRepo;
import com.projet.goodmood.repository.TypemessageRepo;
import com.projet.goodmood.repository.UsersRepo;
import com.projet.goodmood.service.MessageSvc;
import com.projet.goodmood.service.TypemessageSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
@RestController
@RequestMapping("/message")
public class MessageCtrl {
    @Autowired
    MessageSvc messageSvc;
    @Autowired
    UsersRepo usersRepo;

    @Autowired
    MessageRepo messageRepo;
    @Autowired
    TypemessageRepo typemessageRepo;

    @PostMapping("/add/{idusers}/{idtypemessage}")
    public ResponseEntity<?> Createe(@RequestBody Message message, @PathVariable Long idusers, @PathVariable Long idtypemessage) {
        Users users = usersRepo.findById(idusers).get();
        Typemessage typemessage= typemessageRepo.findById(idtypemessage).get();
        String cotenumessage = message.getCotenumessage();
        Message message1 = messageRepo.findByCotenumessage(cotenumessage);

        if (message.getCotenumessage() != ""){

                //typemessage.setAdmin(users);
               // typemessage.setIdtypemessage(typemessage);
            message.setUsersmessage(users);
            message.setTypemassage(typemessage);
                this.messageSvc.Creer(message);
                return ResponseEntity.ok().body(new MessageResponse("message envoyer avec succes"));


        }else {
            return ResponseEntity.ok().body(new MessageResponse("Le contenu du message est obligatoire"));

        }
    }
}
