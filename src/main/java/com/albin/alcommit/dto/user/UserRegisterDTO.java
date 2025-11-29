package com.albin.alcommit.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

// DTO usado para receber os dados do usuário no momento do registro.
// Contém apenas os campos necessários para criar um novo usuário,
// além de validações para garantir que os dados enviados pelo cliente
// estejam corretos antes de chegar ao service.

@Getter
@Setter
public class UserRegisterDTO {

    @NotBlank(message = "O email é obrigatório")
    @Email(message = "Formato de email inválido")
    private String email;

    @NotBlank(message = "O username é obrigatório")
    @Size(min = 3, max = 20, message = "O username deve ter entre 3 e 20 caracteres")
    private String username;

    @NotBlank(message = "A senha é obrigatória")
    @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
    private String password;
}
