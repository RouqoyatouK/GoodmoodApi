package com.projet.goodmood.service;

import com.projet.goodmood.models.Role;
import com.projet.goodmood.models.Users;


import java.util.List;

public interface UsersRoleSvc {

    List<Users> AfficherUser();
    Users modifierUsers(Long id, Users users);
    String supprimerUsers(Long id);
    Users getUsersById(Long id);


    Role Ajouter (Role role);
    List<Role> afficherRoles();
    Role modifierRole(Role role, Long id);
    String SupprimerRole(Long id);


}
