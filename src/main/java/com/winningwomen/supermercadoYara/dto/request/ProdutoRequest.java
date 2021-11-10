package com.winningwomen.supermercadoYara.dto.request;

import lombok.Data;

@Data
public class ProdutoRequest {
	//dados necessarios para criar produtos
	private String nome;
	private int quantidade;
	private float preco;
	
	private Long idCategoria;
	
}
