package com.projet.goodmood.controller;

import com.projet.goodmood.models.ERole;
import com.projet.goodmood.models.Role;
import com.projet.goodmood.models.Users;
import com.projet.goodmood.payload.request.LoginRequest;
import com.projet.goodmood.payload.request.SignupRequest;
import com.projet.goodmood.payload.response.JwtResponse;
import com.projet.goodmood.payload.response.MessageResponse;
import com.projet.goodmood.repository.RoleRepo;
import com.projet.goodmood.repository.UsersRepo;
import com.projet.goodmood.security.jwt.JwtUtils;
import com.projet.goodmood.service.UsersDetailsImpl;
import com.projet.goodmood.service.UsersRoleSvc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
@RequestMapping("/api/auth")
public class AuthController {
    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsersRepo usersRepo;

    @Autowired
    RoleRepo roleRepo;
    @Autowired
    UsersRoleSvc usersRoleSvc;

    //encoder du password
    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    //@Valid assure la validation de l'ensemble de l'objet
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    /*
     AuthenticationManager est comme un coordinateur où vous pouvez enregistrer plusieurs fournisseurs et,
     en fonction du type de demande, il enverra une demande d'authentification au bon fournisseur.
     */

        //authenticate effectue l'authentification avec la requête.

    /*
    Une implémentation importante de l'interface que nous utilisons dans notre exemple de projet
    est DaoAuthenticationProvider, qui récupère les détails de l'utilisateur à partir d'un
    fichier UserDetailsService
     */

        Authentication authentication = authenticationManager.authenticate(
                //on lui fournit un objet avec username et password fournit par l'admin
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    /*
      SecurityContext et SecurityContextHolder sont deux classes fondamentales de Spring Security .
      Le SecurityContext est utilisé pour stocker les détails de l'utilisateur
      actuellement authentifié, également appelé principe. Donc, si vous devez obtenir
      le nom d'utilisateur ou tout autre détail d'utilisateur, vous devez d'abord obtenir
      ce SecurityContext . Le SecurityContextHolder est une classe d'assistance qui permet
      d'accéder au contexte de sécurité.
     */

        //on stocke les informations de connexion de l'utilisateur actuelle souhaiter se connecter dans SecurityContextHolder
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //on envoie encore les infos au generateur du token
        String jwt = jwtUtils.generateJwtToken(authentication);

        //on recupere les infos de l'user
        UsersDetailsImpl usersDetails = (UsersDetailsImpl) authentication.getPrincipal();

        //on recupere les roles de l'users
        List<String> roles = usersDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        log.info("conexion controlleur");

        System.out.println("Username Username" + usersDetails.getUsername() + "Email Email: " + usersDetails.getEmail() + "roles : " +  roles);

        //on retourne une reponse, contenant l'id username, e-mail et le role du collaborateur
        return ResponseEntity.ok(new JwtResponse(jwt,
                /*usersDetails.getId(),
                usersDetails.getUsername(),
                usersDetails.getEmail(),
                roles)*/
                usersDetails.getId(),
                usersDetails.getUsername(),
                usersDetails.getEmail(),
                roles)
        );
    }


    @PostMapping("/signup")//@valid s'assure que les données soit validées
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest
                                          ) {

        if (usersRepo.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Ce nom d'utilisateur existe deja")) ;

        }

        if (usersRepo.existsByEmail(signUpRequest.getEmail())) {


            //confectionne l'objet de retour à partir de ResponseEntity(une classe de spring boot) et MessageResponse
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Erreur: Cet email est déjà utilisé!"));
        }

        System.out.println("rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr:   " + signUpRequest.getRole() + "rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr:   " + signUpRequest.getUsername());


        // Create new user's account
        Users users = new Users(signUpRequest.getUsername(),

                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        //on recupere le role de l'user dans un tableau ordonné de type string
        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();
        System.err.println(strRoles);
        if (strRoles == null) {
            System.out.println("####################################" + signUpRequest.getRole() + "###########################################");

            //on recupere le role de l'utilisateur
            Role userRole = roleRepo.findByName(ERole.ROLE_USER);
            //roles.add(userRole);//on ajoute le role de l'user à roles
            roles.add(userRole);

        } else {
            strRoles.forEach(role -> {//on parcours le role
                switch (role) {
                    case "admin"://si le role est à égale à admin

                        Role adminRole = roleRepo.findByName(ERole.ROLE_ADMIN);
                        roles.add(adminRole);

                        System.out.println("test test : " +  roles);
                        break;
                    default://dans le cas écheant

                        //on recupere le role de l'utilisateur
                        Role userRole = roleRepo.findByName(ERole.ROLE_USER);
                        roles.add(userRole);
                }
            });
        }

        //on ajoute le role au collaborateur
        System.out.println("test " + users.getEmail() + "test "  + users.getUsername() + "test "+ users.getPassword());
        users.setRoles(roles);
        users.setImageusers("http://127.0.0.1/Goodmood/images/profil.png");
        usersRepo.save(users);

        return ResponseEntity.ok(new MessageResponse("user enregistré avec succès!"));
    }

    @GetMapping("/usersid/{id}")
    public ResponseEntity<?> getUsersById(@PathVariable Long id){
        Users users = usersRoleSvc.getUsersById(id);
        if (users ==null){
            return new ResponseEntity<>("Utilisateur introuvable", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(users,HttpStatus.OK);
    }

    @PostMapping("/signout")
    public ResponseEntity<?> logoutAdmin() {
        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(new MessageResponse("Vous vous êtes deconnecter!"));
    }
}
