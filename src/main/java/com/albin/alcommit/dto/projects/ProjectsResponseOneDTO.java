package com.albin.alcommit.dto.projects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectsResponseOneDTO {
    private String name;
    private String status;
    private String location;

    // retornar para o cliente apenas o json
    public ProjectsResponseOneDTO(String name, String status, String location) {
        this.name = name;
        this.status = status;
        this.location = location;
    }
}
