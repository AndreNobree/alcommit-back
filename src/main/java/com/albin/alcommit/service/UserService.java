package com.albin.alcommit.service;

import com.albin.alcommit.dto.user.UserLoginDTO;
import com.albin.alcommit.dto.user.UserRegisterDTO;
import com.albin.alcommit.dto.user.UserResponseDTO;
import com.albin.alcommit.model.User;
import com.albin.alcommit.repository.UserRepository;
import com.albin.alcommit.security.JwtService;
import com.albin.alcommit.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {

    @Autowired//cria e entrega objetos da classe sem instanciá-los com new.
    private UserRepository userRepository;//private UserRepository userRepository = new UserRepositoryImpl();


    @Autowired
    private BCryptPasswordEncoder passwordEncoder; //SecurityConfig (config)

    @Autowired
    private JwtService jwtService; // security

    @Autowired
    private JwtUtil jwtUtil; // security


    /**
     * Registra um novo usuário com os dados vindos do DTO.
     */
    public UserResponseDTO register(UserRegisterDTO dto) {

        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email já está em uso");
        }

        //pega do DTO o email e username para salvar no banco
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setUsername(dto.getUsername());

        //criptografa a senha vinda do DTO na classe SecurityConfig (config) e seta a hash aqui
        String hash = passwordEncoder.encode(dto.getPassword());
        user.setPasswordHash(hash);

        //seta usario q criou o dado e a data
        user.setCreateAt(LocalDateTime.now());
        user.setUpdateAt(LocalDateTime.now());
        user.setCreateBy(1L);
        user.setUpdateBy(1L);

        //manda os dados para o Repository para salvá-los
        User saved = userRepository.save(user);

        // GERAR O TOKEN para retornar para o usuário
        String token = jwtUtil.generateToken(saved.getEmail());

        // RETORNAR O DTO de resposta passando para o usuario apenas o email
        return new UserResponseDTO(saved.getEmail(), token);
    }



    public UserResponseDTO login(UserLoginDTO dto){
        // Buscar usuário pelo email [findByEmail() retorna um Optional<User>.]
        User user = userRepository.findByEmail(dto.getEmail()) //
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // Verificar se senha userHash é diferente da do json
        if (!passwordEncoder.matches(dto.getPassword(), user.getPasswordHash())) {
            throw new RuntimeException("Senha inválida");
        }

        // Gera o token JWT
        String token = jwtService.generateToken(user.getEmail());

        // Retorna apenas o token e email para o cliente
        return new UserResponseDTO(user.getEmail(), token);
    }
}
