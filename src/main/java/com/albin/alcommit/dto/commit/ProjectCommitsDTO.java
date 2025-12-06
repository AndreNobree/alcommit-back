package com.albin.alcommit.dto.commit;

import com.albin.alcommit.dto.projects.ProjectsResponseDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProjectCommitsDTO {
    //DTO response projects (retorna o DTO inteiro)
    private ProjectsResponseDTO project;
    //DTO response commits (lista - 1 projeto pode ter varios commits)
    private List<CommitResponseDTO> commits;

    //registrar a soma de todos os commits pegando por projectID
    private int totalCommits;


    public ProjectCommitsDTO(ProjectsResponseDTO project,
                             List<CommitResponseDTO> commits,
                             int totalCommits) {
        this.project = project;
        this.commits = commits;
        this.totalCommits = totalCommits;
    }
}
