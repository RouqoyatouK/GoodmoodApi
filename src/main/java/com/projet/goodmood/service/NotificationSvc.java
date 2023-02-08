package com.projet.goodmood.service;

import com.projet.goodmood.models.Citation;
import com.projet.goodmood.models.Domaine;

public interface NotificationSvc {

    String generateNotificationByType(Citation citation, Domaine domaine);


}
