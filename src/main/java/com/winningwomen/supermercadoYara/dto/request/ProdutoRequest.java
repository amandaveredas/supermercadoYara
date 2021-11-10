package com.winningwomen.supermercadoYara.dto.request;

import com.winningwomen.supermercadoYara.model.Categoria;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class ProdutoRequest {

	private String nome;
	private int quantidade;
	private BigDecimal preco;
	private long idCategoria;
	private String descricao;
	private String imagem;

}
