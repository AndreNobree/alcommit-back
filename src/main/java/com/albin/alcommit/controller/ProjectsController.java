package com.albin.alcommit.controller;

import com.albin.alcommit.model.Projects;
import com.albin.alcommit.service.ProjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/my-projects")
public class ProjectsController {

    @Autowired
    private ProjectsService projectsService;


    @GetMapping("/all")
    public ResponseEntity<List<Projects>> getAllProjects(){
        List<Projects> projects = projectsService.returnAll();
        return ResponseEntity.ok(projects);
    }
}
