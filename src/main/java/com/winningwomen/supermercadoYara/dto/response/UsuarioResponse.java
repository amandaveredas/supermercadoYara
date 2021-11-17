package com.winningwomen.supermercadoYara.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.winningwomen.supermercadoYara.model.Usuario;
import jdk.jfr.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime data_criacao;
	private String nomeFuncao;
	
    public UsuarioResponse(Usuario usuarioCompleto) {
    }
}
