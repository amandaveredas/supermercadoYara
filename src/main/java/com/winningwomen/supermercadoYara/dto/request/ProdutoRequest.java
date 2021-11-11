package com.winningwomen.supermercadoYara.dto.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class ProdutoRequest {

	@NotEmpty(message = "Campo nome não pode ser nulo ou vazio.")
	private String nome;
	@NotNull(message = "Campo quantidade não pode ser nulo.")
	private Integer quantidade;
	@NotNull(message = "Campo precoUnitario não pode ser nulo.")
	private BigDecimal precoUnitario;
	@NotNull(message = "Campo idCategoria não pode ser nulo.")
	private Long idCategoria;
	private String descricao;
	private String imagem;

}
