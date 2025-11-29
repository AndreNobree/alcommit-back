package com.albin.alcommit.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

//1. Extrair o e-mail (subject) de um token
//        Ou seja, quando chega um token no backend, você usa o JwtUtil para pegar quem é o usuário dentro dele.
//2. Validar partes do token
//    Como:
//    pegar data de expiração
//    verificar se está expirado
//    pegar claims do token
//3. Fazer o parse do token usando a SECRET_KEY
@Component
public class JwtUtil {

    // Chave forte
    private final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // 1 hora de expiração
    private final long expiration = 3600000;

    public String generateToken(String email) {
        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(secretKey)
                .compact();
    }
}
