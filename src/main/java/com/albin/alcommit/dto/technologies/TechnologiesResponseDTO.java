package com.albin.alcommit.dto.technologies;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TechnologiesResponseDTO {
    private String technology;

    // retornar para o cliente apenas o json
    public TechnologiesResponseDTO(String technology) {
        this.technology = technology;
    }
}
