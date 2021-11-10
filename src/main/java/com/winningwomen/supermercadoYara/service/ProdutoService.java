package com.winningwomen.supermercadoYara.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winningwomen.supermercadoYara.dto.request.ProdutoRequest;
import com.winningwomen.supermercadoYara.dto.response.ProdutoResponse;
import com.winningwomen.supermercadoYara.model.Categoria;
import com.winningwomen.supermercadoYara.model.Produto;
import com.winningwomen.supermercadoYara.repository.CategoriaRepository;
import com.winningwomen.supermercadoYara.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	ProdutoRepository produtoRepository;
	@Autowired
	CategoriaRepository categoriaRepository;
	
	public ProdutoResponse create(ProdutoRequest produtoRequest) {
		Categoria categoria = this.categoriaRepository.findById(produtoRequest.getIdCategoria()).orElseThrow(RuntimeException::new);
		Produto produto = new Produto(produtoRequest, categoria);
		Produto produtoCreated = this.produtoRepository.save(produto);
		return new ProdutoResponse(produtoCreated);
	}

}
