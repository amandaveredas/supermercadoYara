package com.winningwomen.supermercadoYara.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProdutoResponse {

	private String nome;
	private int quantidade;
	private BigDecimal precoUnitario;
	private String nomeCategoria;
	private String descricao;
	private String imagem;
}
