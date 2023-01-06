package com.projet.goodmood.controller;

import com.projet.goodmood.models.Role;
import com.projet.goodmood.models.Users;
import com.projet.goodmood.service.UsersRoleSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8101", maxAge = 3600, allowCredentials = "true")
@RequestMapping("/roleuser")
public class UsersRolesCtrl {
    @Autowired
    UsersRoleSvc usersRoleSvc;

    //userrrrrrrrrrrrrrrrrrrrrrrrrrrrr

    @GetMapping("/readusers")
    @PreAuthorize("hasRole('ADMIN') ")
    public List<Users> Afficherutilisateur(){
        return usersRoleSvc.AfficherUser();
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN') ")
    public Users Updateeutilisateur(@RequestBody Users users, @PathVariable Long id){
        return usersRoleSvc.modifierUsers(id, users);
    }


    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN') ")
    public String Supprimerutilisateur(@PathVariable Long id){
        return usersRoleSvc.supprimerUsers(id);
    }



    //roleeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee
    @PostMapping("/addrole")
    @PreAuthorize("hasRole('ADMIN') ")
    public Role Create(@RequestBody Role role){
        return usersRoleSvc.Ajouter(role);
    }

    @GetMapping("/readrole")
    @PreAuthorize("hasRole('ADMIN') ")
    public List<Role> Afficherr(){
        return usersRoleSvc.afficherRoles();
    }

    @PutMapping("/updaterole/{id}")
    @PreAuthorize("hasRole('ADMIN') ")
    public Role Updatee( @RequestBody Role role, @PathVariable Long id){
        return usersRoleSvc.modifierRole(role, id);
    }


    @DeleteMapping("/deleterole/{id}")
    @PreAuthorize("hasRole('ADMIN') ")
    public String Supprimerr(@PathVariable Long id){
        return usersRoleSvc.supprimerUsers(id);
    }
}
