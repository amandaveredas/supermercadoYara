package com.winningwomen.supermercadoYara.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.winningwomen.supermercadoYara.model.Produto;
import com.winningwomen.supermercadoYara.repository.ProdutoRepository;

@RestController
@RequestMapping(value="/produto")
public class ProdutoController {
	
	@Autowired
	ProdutoRepository produtoRepository;
	
	@PostMapping
	public void post(@RequestBody Produto produto) {
		produtoRepository.save(produto);
	}

}
