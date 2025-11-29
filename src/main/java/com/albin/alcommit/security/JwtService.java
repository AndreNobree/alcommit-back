package com.albin.alcommit.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

    // Carrega a secret key do .env
    @Value("${JWT_SECRET}")
    private String SECRET_KEY;

    // -------------------------------
    // 1. Criar token com base nisso q dura uma hora
    // -------------------------------
    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hora
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    // -------------------------------
    // 2. Extrair email do token
    // -------------------------------
    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // -------------------------------
    // 3. Validar token
    // -------------------------------
    public boolean isTokenValid(String token, String email) {
        String extractedEmail = extractEmail(token);
        return email.equals(extractedEmail) && !isTokenExpired(token);
    }

    // -------------------------------
    // Funções auxiliares
    // -------------------------------
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }


//    Ele recebe:
//    o token
//    uma função que diz qual informação você quer extrair do token
//
//    Ele retorna:
//    qualquer tipo de dado do JWT (String, Date, boolean etc.)
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        return claimsResolver.apply(extractAllClaims(token));
    }

    //verifica se o token está expirado
    private boolean isTokenExpired(String token) {
        Date expiration = extractClaim(token, Claims::getExpiration);
        return expiration.before(new Date());
    }
}
