package com.winningwomen.supermercadoYara.dto.response;

import lombok.Data;

@Data
public class ProdutoResponse {

	private Long id;
	private String nome;
	private int quantidade;
	private float preco;
	
}
