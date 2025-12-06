package com.albin.alcommit.service;

import com.albin.alcommit.dto.commit.CommitResponseDTO;
import com.albin.alcommit.dto.commit.ProjectCommitsDTO;
import com.albin.alcommit.dto.projects.ProjectAddDTO;
import com.albin.alcommit.dto.projects.ProjectsResponseDTO;
import com.albin.alcommit.dto.projects.ProjectsResponseOneDTO;
import com.albin.alcommit.model.Commits;
import com.albin.alcommit.model.Projects;
import com.albin.alcommit.model.Technologies;
import com.albin.alcommit.model.User;
import com.albin.alcommit.repository.CommitsRepository;
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

    @Autowired
    private CommitsRepository commitsRepository;

//    //retornar uma lista de todos os projetos
//    public List<ProjectsResponseOneDTO> responseProjectsOne(){
//        //pegar tudo pra depois retornar somente id, nome e status
//        List<Projects> projects = projectsRepository.findAll();
//        //model > response
//        return projects.stream()
//                .map(p -> new ProjectsResponseOneDTO(
//
//                        p.getName(),
//                        p.getStatus(),
//                        p.getLocation()
//                ))
//                .toList();
//    }

    // retorna projeto e status para o front (tela my-projects)
    public List<ProjectsResponseDTO> responseProjectsLite(){
        //pegar tudo pra depois retornar somente id, nome e status
        List<Projects> projects = projectsRepository.findAll();

        //model > response
        return projects.stream()
                .map(p -> new ProjectsResponseDTO(
                        p.getId(),
                        p.getName(),
                        p.getStatus(),
                        p.getLocation(),
                        p.getRepository()
                ))
                .toList();
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

        // salva as tecnologias pegando o DTO do projects (tem uma lista de tecnlogias lá)
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

        return new ProjectsResponseDTO(saved.getId(), saved.getName(), saved.getStatus(), saved.getLocation(), saved.getRepository());
    }

    //retorno para a tela de commits (info de projeto e lista de commits)
    public ProjectCommitsDTO getProjectWithCommits(Long projectId) {

        //procura commits pelo projectID
        Projects project = projectsRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Projeto não encontrado"));

        //preenchimento json projetos
        ProjectsResponseDTO projectDTO = new ProjectsResponseDTO(
                project.getId(),
                project.getName(),
                project.getStatus(),
                project.getLocation(),
                project.getRepository()
        );

        //Carregar os commits
        List<Commits> commits = commitsRepository.findByProjectId(projectId);

        List<CommitResponseDTO> commitDTOs = commits.stream()
                .map(c -> new CommitResponseDTO(
                        c.getMessage(),
                        c.getBranch(),
                        c.getCreateBy(),
                        c.getCreateAt(),
                        c.getUpdateBy(),
                        c.getUpdateAt()
                ))
                .toList();

        // conta os commits existentes
        int total = commits.size();


        return new ProjectCommitsDTO(projectDTO, commitDTOs, total);
    }

}
