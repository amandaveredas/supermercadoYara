package com.winningwomen.supermercadoYara.dto.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProdutoResponse {

	private String nome;
	private int quantidade;
	private BigDecimal preco;
	private long idCategoria;
	private String descricao;
	private String imagem;
}
