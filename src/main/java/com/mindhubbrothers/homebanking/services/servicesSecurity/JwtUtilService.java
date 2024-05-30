package com.mindhubbrothers.homebanking.services.servicesSecurity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtUtilService {

 private static final SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build();
    //metodo que especifica que quiere firmar el token jwt
    //algoritmo de firma que se va a utilizar para firmar el token
    //metodo que indica que esta a punto de especificar la clave que se va a utilizar para firmar el token
    //finalizar la construccion del objeto (token jwt)

 private static final long EXPIRATION_TOKEN = 1000 * 60 * 60;
    //fecha de expiracion del token (1hora)

     //claims:payload del token
 public Claims extractAllClaims(String token){
     return Jwts.parser().verifyWith(SECRET_KEY).build().parseSignedClaims(token).getPayload();
     //Se crea un objeto Jwt parser utilizado para verificar y analizar
     //Verificar token jwt con clave secreta
     //Analiza el token firmado y devuelve un objeto signed jwt (que contiene las claims firmadas)
     //Obtiene y devuelve el cuerpo del token
 }
 //T es generico
 public <T> T extractClaim(String token, Function<Claims, T> claimsTFunction){
     final Claims claims = extractAllClaims(token);
     return claimsTFunction.apply(claims);
    //extraer algun claim en particular
    //puede devolver una fecha, string, etc.
 }

    public String extractUsername(String token){ return extractClaim(token, Claims::getSubject);}

    public Date extractExpiration(String token){ return extractClaim(token, Claims::getExpiration);}

    public boolean isTokenExpired(String token){ return extractExpiration(token).before(new Date());}

    private String createToken(Map<String, Object> claims, String username){
     //Map string-object: clave-valor
     return Jwts
             .builder()
             .claims(claims)
             .subject(username)
             .issuedAt(new Date(System.currentTimeMillis()))
             .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TOKEN))
             .signWith(SECRET_KEY)
             .compact();
    }

    public String generateToken(UserDetails userDetails){
     Map<String, Object> claims = new HashMap<>();
     //estructura para que podamos asociar una clave a un valor (string y rol)
     String rol = userDetails.getAuthorities().iterator().next().getAuthority();
     claims.put("rol", rol);
     return createToken(claims, userDetails.getUsername());
    }

}
