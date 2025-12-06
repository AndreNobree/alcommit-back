package com.albin.alcommit.repository;

import com.albin.alcommit.model.Commits;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommitsRepository  extends JpaRepository<Commits, Long> {

    // Retorna todos os commits criados por um usuário específico [ SELECT * FROM projects WHERE create_by = ? ]
    List<Commits> findByCreateBy(Long userId);

    // busca commits pelo projectID
    List<Commits> findByProjectId(Long projectId);
}
