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
	

	private ProdutoRepository repository;
	private CategoriaService categoriaService;

	@Autowired
	public ProdutoService(ProdutoRepository produtoRepository) {
		this.repository = produtoRepository;
	}

	public void cadastrar(ProdutoRequest produtoRequest) {

		Categoria categoria = categoriaService.buscarPeloId(produtoRequest.getIdCategoria());
		Produto produto = Produto.builder()
				.nome(produtoRequest.getNome())
				.categoria(categoria)
				.descricao(produtoRequest.getDescricao())
				.quantidade(produtoRequest.getQuantidade())
				.preco(produtoRequest.getPreco())
				.imagem(produtoRequest.getImagem())
				.build();

		repository.save(produto);
    }
}
