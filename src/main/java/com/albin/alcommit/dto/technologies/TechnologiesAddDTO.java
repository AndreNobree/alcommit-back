package com.albin.alcommit.dto.technologies;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TechnologiesAddDTO {
    @NotBlank
    private String projectId;

    @NotBlank
    private String technology;
}
