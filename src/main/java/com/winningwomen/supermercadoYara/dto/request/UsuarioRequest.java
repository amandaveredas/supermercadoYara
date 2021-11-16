package com.winningwomen.supermercadoYara.dto.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Data
public class UsuarioRequest {


    @NotEmpty(message = "Campo user name não pode ser nulo ou vazio.")
    private String user_name;
    
    @NotEmpty(message = "Campo nome não pode ser nulo ou vazio.")
    private String nome;
    
    @NotEmpty(message = "Campo sobrenome não pode ser nulo ou vazio.")
    private String sobrenome;
    
    @NotEmpty(message = "Campo email não pode ser nulo ou vazio.")
    private String email;
    
    @NotEmpty(message = "Campo senha não pode ser nulo ou vazio.")
    private String senha;
    
    private LocalDate data_criacao;
    
    private Long funcao;

    public Long getIdFuncao() {
        return funcao;
    }
}
