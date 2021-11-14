package com.winningwomen.supermercadoYara.controller;

import com.winningwomen.supermercadoYara.dto.request.ProdutoRequest;
import com.winningwomen.supermercadoYara.dto.response.ProdutoResponse;
import com.winningwomen.supermercadoYara.exception.AmbiguidadeDeNomesProdutosException;
import com.winningwomen.supermercadoYara.exception.CategoriaNaoExisteException;
import com.winningwomen.supermercadoYara.exception.ProdutoNaoExisteException;
import com.winningwomen.supermercadoYara.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value="/produtos")
public class ProdutoController {
	
	private ProdutoService produtoService;

	@Autowired
	public ProdutoController(ProdutoService produtoService) {
		this.produtoService = produtoService;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void cadastrarProduto(@RequestBody @Valid ProdutoRequest produtoRequest) throws AmbiguidadeDeNomesProdutosException, CategoriaNaoExisteException {
		produtoService.cadastrar(produtoRequest);
	}

	@GetMapping
	public List<ProdutoResponse> listarTodosProdutos () {
		return produtoService.listarTodosOrdemAlfabetica();
	}

	@PutMapping("/{id}")
	public ProdutoResponse alterarProduto(@PathVariable Long id, @RequestBody @Valid ProdutoRequest produtoRequest) throws CategoriaNaoExisteException, ProdutoNaoExisteException {
		return produtoService.alterar(id, produtoRequest);
	}


}
