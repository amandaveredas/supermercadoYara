package com.winningwomen.supermercadoYara.controller;

import com.winningwomen.supermercadoYara.dto.request.ProdutoRequest;
import com.winningwomen.supermercadoYara.dto.response.ProdutoResponse;
import com.winningwomen.supermercadoYara.exception.*;
import com.winningwomen.supermercadoYara.service.ImagemService;
import com.winningwomen.supermercadoYara.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value="/produtos")
public class ProdutoController {
	
	private ProdutoService produtoService;
	private ImagemService imagemService;

	@Autowired
	public ProdutoController(ProdutoService produtoService, ImagemService imagemService) {
		this.produtoService = produtoService;
		this.imagemService = imagemService;
	}

	@PostMapping(consumes = {"multipart/form-data"})
	@ResponseStatus(HttpStatus.CREATED)
	public void cadastrarProduto(@RequestHeader HttpHeaders headers, @ModelAttribute @Valid ProdutoRequest produtoRequest) throws AmbiguidadeDeNomesProdutosException, CategoriaNaoExisteException, UsuarioNaoEAdministradorException, UsuarioNaoLogadoException {
		produtoService.cadastrar(headers,produtoRequest);
	}

	@GetMapping
	public List<ProdutoResponse> listarTodosProdutos (@RequestHeader HttpHeaders headers) throws UsuarioNaoLogadoException {
		return produtoService.listarTodosOrdemAlfabetica(headers);
	}

	@PutMapping(value = "/{id}", consumes = {"multipart/form-data"})
	public ProdutoResponse alterarProduto(@RequestHeader HttpHeaders headers,@PathVariable Long id, @ModelAttribute @Valid ProdutoRequest produtoRequest) throws CategoriaNaoExisteException, ProdutoNaoExisteException, UsuarioNaoEAdministradorException, UsuarioNaoLogadoException {
		return produtoService.alterar(headers, id, produtoRequest);
	}

	@DeleteMapping("/{id}")
	public void excluirProduto(@RequestHeader HttpHeaders headers, @PathVariable Long id) throws ProdutoNaoExisteException, UsuarioNaoLogadoException, UsuarioNaoEAdministradorException {
		produtoService.excluir(headers, id);
	}

	@PostMapping("/exportar")
	public void exportarExcel(@RequestHeader HttpHeaders headers) throws UsuarioNaoLogadoException {
		produtoService.exportar(headers);
	}

}
