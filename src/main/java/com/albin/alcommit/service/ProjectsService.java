package com.albin.alcommit.service;

import com.albin.alcommit.dto.projects.ProjectAddDTO;
import com.albin.alcommit.dto.projects.ProjectsResponseDTO;
import com.albin.alcommit.model.Projects;
import com.albin.alcommit.model.Technologies;
import com.albin.alcommit.model.User;
import com.albin.alcommit.repository.ProjectsRepository;
import com.albin.alcommit.repository.TechnologiesRepository;
import com.albin.alcommit.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProjectsService {

    @Autowired
    private ProjectsRepository projectsRepository;

    @Autowired
    private TechnologiesRepository technologiesRepository;

    @Autowired
    private UserRepository userRepository;

    //retornar uma lista de todos os projetos
    public List<Projects> returnAll(){
        return projectsRepository.findAll();
    }

    public ProjectsResponseDTO addNewProject(ProjectAddDTO dto){

        // pega quem está logado
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (projectsRepository.existsByName(dto.getName())) {
            throw new RuntimeException("O nome do projeto já está em uso");
        }
        if (projectsRepository.existsByRepository(dto.getRepository())) {
            throw new RuntimeException("O repositório já está em uso");
        }
        if (projectsRepository.existsByLocation(dto.getLocation())) {
            throw new RuntimeException("A Path já está com outro projeto alocado");
        }

        Projects projects = new Projects();
        //setEmail e setUsername é do lombok no model
        projects.setName(dto.getName());
        projects.setStatus(dto.getStatus());
        projects.setRepository(dto.getRepository());
        projects.setLocation(dto.getLocation());

        //seta usario q criou o dado e a data
        projects.setCreateAt(LocalDateTime.now());
        projects.setUpdateAt(LocalDateTime.now());
        projects.setCreateBy(user.getId());
//        projects.setUpdateBy(1L);

        //salva os projetos
        Projects saved = projectsRepository.save(projects);

        // salva as tecnologias
        if (dto.getTechnologies() != null) {
            for (String techName : dto.getTechnologies()) {

                Technologies tech = new Technologies();
                tech.setProjectID(saved.getId());
                tech.setTechnology(techName);
                tech.setCreateAt(LocalDateTime.now());
                tech.setUpdateAt(LocalDateTime.now());
                projects.setCreateBy(user.getId());

                technologiesRepository.save(tech);
            }
        }

        return new ProjectsResponseDTO(saved.getName(), saved.getStatus());
    }
}
