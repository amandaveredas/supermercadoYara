package com.winningwomen.supermercadoYara.dto.response;

import java.time.LocalDate;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.winningwomen.supermercadoYara.model.Funcao;
import com.winningwomen.supermercadoYara.model.Usuario;

import lombok.Data;

@Data
public class UsuarioResponse {
	
	
	public UsuarioResponse(Usuario usuario) {
		this.setNome(usuario.getNome());
		this.setSobrenome(usuario.getSobrenome());
		this.setEmail(usuario.getEmail());
		this.setSenha(usuario.getSenha());
		this.setData_criacao(usuario.getData_criacao());
		Funcao funcao = new Funcao();
		this.setFuncao(funcao);
	}
	
	private String nome;
	private String sobrenome;
	private String email;
	private String senha;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate data_criacao;
	private Funcao funcao;



}
