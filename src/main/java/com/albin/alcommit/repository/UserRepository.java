//interface que acessa o banco.
package com.albin.alcommit.repository;

import com.albin.alcommit.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
//O Jpa é a interface que faz funcionar(Buscar por ID (findById), Buscar todos (findAll), Salvar (save), Deletar (delete), Paginação, Ordenação)

import org.springframework.stereotype.Repository; //@repository

import java.util.Optional; //previne o NullPointException

@Repository
public interface UserRepository extends JpaRepository<User, Long> { //User = entidade User, Long = tipo do ID (se fosse UUID, seria <User, UUID>)
//<User, Long> especifica com qual id(registro) vamos trabalhar

    // Buscar por email (Spring cria essa query automaticamente[ SELECT * FROM users WHERE email = ? ])
    Optional<User> findByEmail(String email);

    //Verifica se o email existe [SELECT COUNT(*) > 0 FROM users WHERE email = ? ]
    boolean existsByEmail(String email);

}


