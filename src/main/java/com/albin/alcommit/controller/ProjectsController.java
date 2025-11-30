package com.albin.alcommit.controller;

import com.albin.alcommit.dto.projects.ProjectAddDTO;
import com.albin.alcommit.dto.projects.ProjectsResponseDTO;
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


    @GetMapping("/myprojects")
    public ResponseEntity<List<Projects>> getAllProjects(){
        List<Projects> projects = projectsService.returnAll();
        return ResponseEntity.ok(projects);
    }

    @PostMapping("/addprojects")
    public ResponseEntity<ProjectsResponseDTO> newProject(@Valid @RequestBody ProjectAddDTO dto) {
        //manda os dados para o service passando pelo DTO primeiro
        ProjectsResponseDTO createdProject = projectsService.addNewProject(dto);

        //manda sucess(200) para o cliente
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProject);
    }

}
