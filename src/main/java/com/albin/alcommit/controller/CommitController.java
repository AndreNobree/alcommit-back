package com.albin.alcommit.controller;

import com.albin.alcommit.model.Projects;
import com.albin.alcommit.service.ProjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CommitController {

    @Autowired
    private ProjectsService projectsService;

//    @GetMapping("/commit")
//    public ResponseEntity<List<Projects>> getProjectCommit() {
//        List<Projects> projects = projectsService.returnAll();
//        return ResponseEntity.ok(projects);
//    }
}
