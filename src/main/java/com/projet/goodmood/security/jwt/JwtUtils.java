package com.projet.goodmood.security.jwt;

import com.projet.goodmood.service.UsersDetailsImpl;
import io.jsonwebtoken.*;
import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {
    //cette classe genère le token
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${bezkoder.app.jwtSecret}")
    private String jwtSecret;

    @Value("${bezkoder.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    @Value("bezkoder")
    private String jwtCookie;


    public ResponseCookie getCleanJwtCookie() {
        ResponseCookie cookie = ResponseCookie.from(jwtCookie, null).path("/api").build();
        return cookie;
    }
    public String generateJwtToken(Authentication authentication) {

        UsersDetailsImpl userPrincipal = (UsersDetailsImpl) authentication.getPrincipal();

        //generation du token
        return Jwts.builder()
                .setSubject((userPrincipal.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)//SignatureAlgorithm.HS512
                .compact();//HS256
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }


    //verifie la validité du JWT
    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error("Signature JWT non valide: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Jeton JWT non valide: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("Le jeton JWT a expiré: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("Le jeton JWT n'est pas pris en charge: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("La chaîne de revendications JWT est vide: {}", e.getMessage());
        }

        return false;
    }



}
