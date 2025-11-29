package com.albin.alcommit.service;

import com.albin.alcommit.model.Projects;
import com.albin.alcommit.repository.ProjectsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectsService {

    @Autowired
    private ProjectsRepository projectsRepository;

    //retornar uma lista de todos os projetos
    public List<Projects> returnAll(){
        return projectsRepository.findAll();
    }
}
