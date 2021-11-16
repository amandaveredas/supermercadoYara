package com.winningwomen.supermercadoYara.dto.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Data
@Getter
@Setter
public class LoginUsuarioRequest {

    @NotNull(message = "O campo email não pode ser nulo.")
    private String email;
    @NotNull(message = "O campo senha não pode ser nulo.")
    private String senha;
}
