package com.winningwomen.supermercadoYara.dto.request;

import java.time.LocalDate;
import java.util.Date;

import javax.validation.constraints.PastOrPresent;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class UsuarioRequest {
	
	private String user_name;
	private String nome;
	private String sobrenome;
	private String email;
	private String senha;
	
	@PastOrPresent
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate data_criacao;
	
	private Long idFuncao;


}
