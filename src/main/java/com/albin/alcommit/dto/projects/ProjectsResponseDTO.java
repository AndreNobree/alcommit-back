package com.albin.alcommit.dto.projects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectsResponseDTO {
    private Long id;
    private String name;
    private String status;
    private String location;
    private String repository;

    // retornar para o cliente apenas o json
    public ProjectsResponseDTO(Long id, String name, String status, String location, String repository) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.location = location;
        this.repository = repository;
    }
}
