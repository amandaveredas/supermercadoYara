package com.winningwomen.supermercadoYara.dto.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CategoriaRequest {
	@NotNull(message = "Campo username não pode ser nulo.")
	@NotEmpty(message = "Campo user name não pode ser vazio.")
	private String nome;

}
