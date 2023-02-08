package com.projet.goodmood.repository;

import com.projet.goodmood.models.Citation;
import com.projet.goodmood.models.Domaine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CitationRepo extends JpaRepository<Citation, Long> {


   /*
    @Query ( value= "select * from citation, domaine where iddomaine=:iddomaine", nativeQuery = true)
    public List<Citation> Affichertoulescitationdundomain(Long iddomaine);
    SELECT * FROM `citation`,domaine_user,users,domaine
    WHERE users.id = domaine_user.user_id
    AND domaine.iddomaine = domaine_user.domaine_id
    AND domaine.iddomaine = citation.domaines
    AND users.id=5;
                            */

   /* @Query(value= "select * from citation, domaine_user, users, domaine where users.id = domaine_user.user_id AND domaine.iddomaine = domaine_user.domaine_id AND domaine.iddomaine = citation.domaine AND users.id=: users.id", nativeQuery = true)
    public List<Citation> Affichertoulescitationdundomain(Long id);*/

    @Query(value="select * from citation, domaine_user, users, domaine where users.id = domaine_user.user_id AND domaine.iddomaine = domaine_user.domaine_id AND domaine.iddomaine = citation.domaines. AND users.id =:id", nativeQuery = true)
    public List<Citation> Affichertoulescitationdundomain(Long id);

/*
    select * from citation, domaine_user, users, domaine where users.id = domaine_user.userss AND domaine.iddomaine = domaine_user.domaine_id AND domaine.iddomaine = citation.domaine AND users.id=: users.id"
*/



    //SELECT * FROM `favoris_users`  WHERE user_id=5

    Citation findByEcrivain(String ecrivain);


}
