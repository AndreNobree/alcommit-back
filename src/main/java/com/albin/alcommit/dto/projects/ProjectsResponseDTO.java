package com.albin.alcommit.dto.projects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectsResponseDTO {
    private String name;
    private String status;

    // retornar para o cliente apenas o json
    public ProjectsResponseDTO(String name, String status) {
        this.name = name;
        this.status = status;
    }
}
