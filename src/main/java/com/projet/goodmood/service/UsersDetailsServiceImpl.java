package com.projet.goodmood.service;

import com.projet.goodmood.models.Users;
import com.projet.goodmood.repository.RoleRepo;
import com.projet.goodmood.repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsersDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UsersRepo usersRepo;

    @Autowired
    RoleRepo roleRepo;

    //recupere les details du collaborateur
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = usersRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("collaborateur non trouvé: " + username));

        return UsersDetailsImpl.build(users);
    }
/*
    @Override
    public String modifierUsers(Users users) {
        if (usersRepo.findById(users.getId()) !=null) {
            return usersRepo.findById(users.getId())
                    .map(c -> {
                        c.setPassword(users.getPassword());
                        c.setEmail(users.getEmail());
                        usersRepo.save(c);
                        return  "Modifié Reçu avec succes";

                    }).orElseThrow(() -> new RuntimeException("Collaborateurs non trouvée !"));
        }else {
            return "Modification échoué";
        }
    }



    @Override
    public List<Role> afficherRoles() {
        return roleRepo.findAll();
    }

    @Override
    public String modifierRole(Role role) {
        if (roleRepo.findById(role.getId()) !=null) {
            return roleRepo.findById(role.getId())
                    .map(r -> {
                        r.setName(role.getName());
                        roleRepo.save(r);
                        return  "roles modifié avec succes";

                    }).orElseThrow(() -> new RuntimeException("Activité non trouvée !"));
        }else {
            return "Modification échoué";
        }
    }


    @Override
    public String supprimerRole(Long id) {
        if(roleRepo.findById(id).get() != null){
            roleRepo.deleteById(id);

            return "Modification Reçu";
        }else {
            return "Modification échoué";
        }
    }


    @Override
    public String supprimerCollaborateur(Long id) {
        if(usersRepo.findById(id).get() != null){
            usersRepo.deleteById(id);

            return "Modification reçue";
        }else{
            return null;
        }

    }

     */

}
