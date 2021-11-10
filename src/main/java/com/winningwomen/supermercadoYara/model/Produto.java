package com.winningwomen.supermercadoYara.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.winningwomen.supermercadoYara.dto.request.ProdutoRequest;

import lombok.Data;
@Data
@Entity
@Table(name="produto")
public class Produto {

	public Produto(ProdutoRequest produtoRequest, Categoria categoria) {
		this.setNome(produtoRequest.getNome());
		this.setPreco(produtoRequest.getPreco());
		this.setQuantidade(produtoRequest.getQuantidade());
		this.setCategoria(categoria);
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private int quantidade;
	private float preco;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "categoria_id")
	@JsonIgnore 
	private Categoria categoria;
	
}
