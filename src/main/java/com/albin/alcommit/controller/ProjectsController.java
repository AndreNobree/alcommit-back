package com.albin.alcommit.controller;

import com.albin.alcommit.dto.commit.ProjectCommitsDTO;
import com.albin.alcommit.dto.projects.ProjectAddDTO;
import com.albin.alcommit.dto.projects.ProjectsResponseDTO;
import com.albin.alcommit.dto.projects.ProjectsResponseOneDTO;
import com.albin.alcommit.model.Projects;
import com.albin.alcommit.service.ProjectsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
@CrossOrigin(origins = "http://localhost:3000")
public class ProjectsController {

    @Autowired
    private ProjectsService projectsService;


    //tela home
    @GetMapping("/myprojects")
    public ResponseEntity<List<ProjectsResponseDTO>> getProjectsLite() {
        List<ProjectsResponseDTO> projects = projectsService.responseProjectsLite();
        return ResponseEntity.ok(projects);
    }

    //tela para adicionar projetos
    @PostMapping("/addprojects")
    public ResponseEntity<ProjectsResponseDTO> newProject(@Valid @RequestBody ProjectAddDTO dto) {
        //manda os dados para o service passando pelo DTO primeiro
        ProjectsResponseDTO createdProject = projectsService.addNewProject(dto);

        //manda sucess(200) para o cliente
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProject);
    }

    //tela de commits, (recebe o projectID e retorna o project e commits
    @GetMapping("/commits/{projectId}")
    public ResponseEntity<ProjectCommitsDTO> getProjectAndCommits(@PathVariable Long projectId) {

        ProjectCommitsDTO data = projectsService.getProjectWithCommits(projectId);
        return ResponseEntity.ok(data);
    }


//    // tela de commits (onde vai ser retornados infos do projeto)
//    @GetMapping("/commit")
//    public ResponseEntity<List<ProjectsResponseOneDTO>> getProjectsOne() {
//        List<ProjectsResponseOneDTO> projects = projectsService.responseProjectsOne();
//        return ResponseEntity.ok(projects);
//    }

}
