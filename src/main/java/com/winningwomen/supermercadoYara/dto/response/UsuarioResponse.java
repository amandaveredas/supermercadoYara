package com.winningwomen.supermercadoYara.dto.response;

import com.winningwomen.supermercadoYara.model.Funcao;
import com.winningwomen.supermercadoYara.model.Usuario;
import jdk.jfr.Timestamp;
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
	@Timestamp
    private LocalDate data_criacao;
	private Funcao funcao;

    public UsuarioResponse(Usuario usuarioCompleto) {
    }
}
