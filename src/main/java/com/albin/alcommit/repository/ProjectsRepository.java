package com.albin.alcommit.repository;

import com.albin.alcommit.model.Projects;

import org.springframework.data.jpa.repository.JpaRepository;
//O Jpa é a interface que faz funcionar(Buscar por ID (findById), Buscar todos (findAll), Salvar (save), Deletar (delete), Paginação, Ordenação)

import org.springframework.stereotype.Repository; //@repository

import java.util.List;

@Repository
public interface ProjectsRepository extends JpaRepository<Projects, Long> {
    //<User, Long> especifica com qual id(registro) vamos trabalhar

    // Retorna todos os projetos criados por um usuário específico [ SELECT * FROM projects WHERE create_by = ? ]
    List<Projects> findByCreateBy(Long userId);
}
