package com.winningwomen.supermercadoYara.dto.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UsuarioRequest {

    @NotNull(message = "Campo username não pode ser nulo.")
    @NotEmpty(message = "Campo username não pode ser vazio.")
    private String userName;
    @NotNull(message = "Campo nome não pode ser nulo.")
    @NotEmpty(message = "Campo nome não pode ser vazio.")
    private String nome;
    @NotNull(message = "Campo sobrenome não pode ser nulo.")
    @NotEmpty(message = "Campo sobrenome não pode ser vazio.")
    private String sobrenome;
    @NotNull(message = "Campo email não pode ser nulo.")
    @NotEmpty(message = "Campo email não pode ser vazio.")
    private String email;
    @NotNull(message = "Campo senha não pode ser nulo.")
    @NotEmpty(message = "Campo senha não pode ser vazio.")
    private String senha;
    @NotNull(message = "Campo funcao não pode ser nulo.")
    private String nomeFuncao;

    public String nomeFuncao() {
        return nomeFuncao;
    }
}
