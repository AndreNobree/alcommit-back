package com.albin.alcommit.dto.projects;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectAddDTO {
    @NotBlank
    private String name;

    @NotBlank
    private String status;

    @NotBlank
    private String repository;

    @NotBlank
    private String location;

}
