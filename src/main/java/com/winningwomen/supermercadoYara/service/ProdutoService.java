package com.winningwomen.supermercadoYara.service;

import com.winningwomen.supermercadoYara.exception.AmbiguidadeDeNomesProdutosException;
import com.winningwomen.supermercadoYara.exception.CategoriaNaoExisteException;
import com.winningwomen.supermercadoYara.model.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winningwomen.supermercadoYara.dto.request.ProdutoRequest;
import com.winningwomen.supermercadoYara.model.Produto;
import com.winningwomen.supermercadoYara.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	private ProdutoRepository repository;
	private CategoriaService categoriaService;

	@Autowired
	public ProdutoService(ProdutoRepository repository, CategoriaService categoriaService) {
		this.repository = repository;
		this.categoriaService = categoriaService;
	}

	public void cadastrar(ProdutoRequest produtoRequest) throws AmbiguidadeDeNomesProdutosException, CategoriaNaoExisteException {

		if(repository.existsByNome(produtoRequest.getNome()))
			throw new AmbiguidadeDeNomesProdutosException(produtoRequest.getNome());

		Categoria categoria = categoriaService.buscarPeloId(produtoRequest.getIdCategoria());

				Produto produto = Produto.builder()
				.nome(produtoRequest.getNome())
				.categoria(categoria)
				.descricao(produtoRequest.getDescricao())
				.quantidade(produtoRequest.getQuantidade())
				.precoUnitario(produtoRequest.getPrecoUnitario())
				.imagem(produtoRequest.getImagem())
				.build();

		repository.save(produto);
    }
}
