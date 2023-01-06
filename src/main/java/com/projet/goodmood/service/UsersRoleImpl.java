package com.projet.goodmood.service;


import com.projet.goodmood.models.Role;
import com.projet.goodmood.models.Users;
import com.projet.goodmood.repository.RoleRepo;
import com.projet.goodmood.repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersRoleImpl implements UsersRoleSvc {
    @Autowired
    UsersRepo usersRepo;
    @Autowired
    RoleRepo roleRepo;


    @Override
    public List<Users> AfficherUser() {
        return usersRepo.findAll();
    }

    @Override
    public Users modifierUsers(Long id, Users users) {
        return usersRepo.findById(id).map(u->{
            u.setUsername(users.getUsername());
            u.setEmail(users.getEmail());
            u.setPassword(users.getPassword());
            u.setRoles(users.getRoles());
            return usersRepo.save(u);
        }).orElseThrow(()->new RuntimeException("users trouver"));
    }

    @Override
    public String supprimerUsers(Long id) {
        this.usersRepo.deleteById(id);
        return "supprimer avec succes";
    }

    @Override
    public Role Ajouter(Role role) {
        return roleRepo.save(role);
    }

    @Override
    public List<Role> afficherRoles() {
        return roleRepo.findAll();
    }

    @Override
    public Role modifierRole(Role role, Long id) {
        return roleRepo.findById(id).map(r->{
            r.setName(role.getName());
            return roleRepo.save(r);
        }).orElseThrow(()->new RuntimeException("users trouver"));
    }

    @Override
    public String SupprimerRole(Long id) {
        this.roleRepo.deleteById(id);
        return "supprimer avec succes";
    }
}
