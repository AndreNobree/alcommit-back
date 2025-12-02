package com.albin.alcommit.security;


import com.albin.alcommit.model.User;
import com.albin.alcommit.repository.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;


@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

//    Esse metodo é executado em toda requisição que chega no backend.
//    Antes do controller, antes do service, antes de qualquer coisa.
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        // Pega o header Authorization do HTTP
        String authHeader = request.getHeader("Authorization");

        //Se não existir ou não começar com "Bearer", passa direto
//        Usuário não enviou token → deixa passar (mas depois o Security bloqueia)
//        Usuário enviou algo como "Basic 123" → ignora


        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        //Extrai o token
        String token = authHeader.substring(7);
        //Extrai o email que está dentro do token
        String email = jwtService.extractEmail(token);

        //Verifica se já existe um usuário autenticado
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            //Busca o usuário no banco
            User user = userRepository.findByEmail(email)
                    .orElse(null);

            //Verifica token + usuário
            if (user != null && jwtService.isTokenValid(token, email)) {

                CustomUserDetails userDetails = new CustomUserDetails(user);
                // coloca o email no token
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }


        }
        //Continua o fluxo normal da requisição
        filterChain.doFilter(request, response);
    }
}