package com.albin.alcommit.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDTO {
    private String email;
    private String token;

    // retornar para o cliente apenas o json
    public UserResponseDTO(String email, String token) {
        this.email = email;
        this.token = token;
    }
}
