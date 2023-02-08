package com.projet.goodmood.service;

import com.projet.goodmood.models.Citation;
import com.projet.goodmood.models.Domaine;
import com.projet.goodmood.models.Notification;
import com.projet.goodmood.models.Users;
import com.projet.goodmood.repository.NotificationRepo;
import com.projet.goodmood.repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class NotificationImpl implements NotificationSvc{

    @Autowired
    NotificationRepo notificationRepo;
    @Autowired
    UsersRepo usersRepo;

    @Override
    public String generateNotificationByType(Citation citation, Domaine domaine) {
        String message = "une nouvelle citation du domaine" + " " + domaine.getNomdomaine() + "vient d'être partager. Nous vous invitons à aller consulter.";
        Notification notification = new Notification();

        notification.setMessage(message);
        notification.setDatenotif(new Date());
        notificationRepo.save(notification);

        //Users users= usersRepo.findById(idusers).get();
        for (Users users : domaine.getUserss()){
            users.getNotifications().add(notification);
            usersRepo.save(users);
        }

        return "notif creer avec succes";
    }
}



/*@Override
    public String generateNotificationByType(Projets projets, Typeprojet typeprojet) {
        String message = "Un nouveau projet de type " + typeprojet.getNomtype() + " vient d'être lancé, " +
                projets.getNomprojets() + " .Nous vous invitons à consulter les détails de ce projet. ";
        Notification notification = new Notification();
        notification.setMessage(message);
        notification.setDatenotif(new Date());
        notificationRepository.save(notification);
        for (Investisseur investisseur : typeprojet.getInvestisseurs()){
            investisseur.getNotifications().add(notification);
            investisseurReposotory.save(investisseur);
        }
        return "Notification créé avec succès";
    }*/
