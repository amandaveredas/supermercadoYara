package com.winningwomen.supermercadoYara.controller;

import com.winningwomen.supermercadoYara.dto.request.ProdutoRequest;
import com.winningwomen.supermercadoYara.dto.response.ProdutoResponse;
import com.winningwomen.supermercadoYara.exception.AmbiguidadeDeNomesProdutosException;
import com.winningwomen.supermercadoYara.exception.CategoriaNaoExisteException;
import com.winningwomen.supermercadoYara.exception.ProdutoNaoExisteException;
import com.winningwomen.supermercadoYara.exception.UsuarioNaoLogadoExcetion;
import com.winningwomen.supermercadoYara.service.ImagemService;
import com.winningwomen.supermercadoYara.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
	public void cadastrarProduto(@ModelAttribute @Valid ProdutoRequest produtoRequest) throws AmbiguidadeDeNomesProdutosException, CategoriaNaoExisteException {
		produtoService.cadastrar(produtoRequest);
	}

	@GetMapping
	public List<ProdutoResponse> listarTodosProdutos () {
		return produtoService.listarTodosOrdemAlfabetica();
	}

	@PutMapping(value = "/{id}", consumes = {"multipart/form-data"})
	public ProdutoResponse alterarProduto(@PathVariable Long id, @ModelAttribute @Valid ProdutoRequest produtoRequest) throws CategoriaNaoExisteException, ProdutoNaoExisteException {
		return produtoService.alterar(id, produtoRequest);
	}

	@DeleteMapping("/{id}")
	public void excluirProduto(@RequestHeader HttpHeaders headers, @PathVariable Long id) throws ProdutoNaoExisteException, UsuarioNaoLogadoExcetion {
		produtoService.excluir(headers, id);
	}

	@PostMapping("/exportar")
	public void exportarExcel(){
		produtoService.exportar();
	}

//	@PostMapping("/imagem/upload")
//	public String salvarImagem(@RequestParam(value = "arquivo")MultipartFile arquivo){
//		return imagemService.salvarImagem(arquivo);
//	}
}
