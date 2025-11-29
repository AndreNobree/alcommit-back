package com.albin.alcommit.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginDTO {
    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;
}