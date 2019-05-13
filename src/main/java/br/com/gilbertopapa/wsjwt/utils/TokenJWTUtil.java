package br.com.gilbertopapa.wsjwt.utils;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;


public class TokenJWTUtil {


    private static KeyGenerator keyGenerator = new KeyGenerator();


    public static String gerarToken(String username, List<String> roles){
        Key key = keyGenerator.generateKey();

        String jwtToken = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256,key)
                .setHeaderParam("typ","JWT")
                .setSubject(username)
                .setIssuer("Gilberto")
                .setIssuedAt(new Date())
                .setExpiration(toDate(LocalDateTime.now().plusMinutes(60L)))
                .claim("roles",roles)
                .compact();

        return jwtToken;
    }


    private static final Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }



}
