package com.albin.alcommit.controller;

import com.albin.alcommit.dto.user.UserLoginDTO;
import com.albin.alcommit.dto.user.UserRegisterDTO;
import com.albin.alcommit.dto.user.UserResponseDTO;
import com.albin.alcommit.service.UserService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth") //endpoint - /users/register
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired//cria e entrega objetos da classe sem instanciá-los com new.
    private UserService userService;//private UserService userService = new UserService();

    /**
     * Endpoint para registrar um novo usuário.
     * Recebe um DTO, valida os dados e delega para o service.
     */
    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@Valid @RequestBody UserRegisterDTO dto) {

        //manda os dados para o service passando pelo DTO primeiro
        UserResponseDTO createdUser = userService.registerDefault(dto);

        //manda sucess(200) para o cliente
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }


    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> login(@Valid @RequestBody UserLoginDTO dto) {
        // pega a resposta do servidor (erro ou email + token)
        UserResponseDTO response = userService.login(dto);

        return ResponseEntity.ok(response); //status 200 + reposta do servidor

    }

}
