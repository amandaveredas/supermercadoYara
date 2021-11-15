package com.winningwomen.supermercadoYara.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioResponse {

    private String user_name;
    private String nome;
    private String sobrenome;
    private String email;
    private String senha;
    private LocalDate data_criacao;
}
