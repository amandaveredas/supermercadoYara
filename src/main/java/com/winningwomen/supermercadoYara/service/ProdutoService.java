package com.winningwomen.supermercadoYara.service;

import com.winningwomen.supermercadoYara.dto.response.ProdutoResponse;
import com.winningwomen.supermercadoYara.exception.AmbiguidadeDeNomesProdutosException;
import com.winningwomen.supermercadoYara.exception.CategoriaNaoExisteException;
import com.winningwomen.supermercadoYara.exception.ProdutoNaoExisteException;
import com.winningwomen.supermercadoYara.model.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winningwomen.supermercadoYara.dto.request.ProdutoRequest;
import com.winningwomen.supermercadoYara.model.Produto;
import com.winningwomen.supermercadoYara.repository.ProdutoRepository;

import javax.transaction.Transactional;
import java.util.*;


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

	public List<ProdutoResponse> listarTodosOrdemAlfabetica() {
		List<Produto> produtos = repository.findAllByOrderByNomeAsc();
		List<ProdutoResponse> listaProdutosResponse = new ArrayList<>();
		for(Produto p: produtos){
			ProdutoResponse produtoResponse = ProdutoResponse.builder()
					.nome(p.getNome())
					.descricao(p.getDescricao())
					.nomeCategoria(p.getCategoria().getNome())
					.precoUnitario(p.getPrecoUnitario())
					.quantidade(p.getQuantidade())
					.imagem(p.getImagem())
					.build();
			listaProdutosResponse.add(produtoResponse);
		}

		return listaProdutosResponse;
	}

	public ProdutoResponse alterar(Long id, ProdutoRequest produtoRequest) throws ProdutoNaoExisteException, CategoriaNaoExisteException {
		Produto produto = buscaProduto(id);
		Categoria categoria = categoriaService.buscarPeloId(produtoRequest.getIdCategoria());

		produto.setNome(produtoRequest.getNome());
		produto.setCategoria(categoria);
		produto.setDescricao(produtoRequest.getDescricao());
		produto.setQuantidade(produtoRequest.getQuantidade());
		produto.setPrecoUnitario(produtoRequest.getPrecoUnitario());
		produto.setImagem(produtoRequest.getImagem());

		repository.save(produto);

		ProdutoResponse produtoResponse = ProdutoResponse.builder()
				.nome(produto.getNome())
				.nomeCategoria(categoria.getNome())
				.descricao(produto.getDescricao())
				.quantidade(produto.getQuantidade())
				.precoUnitario(produto.getPrecoUnitario())
				.imagem(produto.getImagem())
				.build();

		return  produtoResponse;
	}

	private Produto buscaProduto(Long id) throws ProdutoNaoExisteException {
		if(!repository.existsById(id))
			throw new ProdutoNaoExisteException(id);
		Produto produto = repository.findById(id).get();
		return produto;
	}

	@Transactional
	public void excluir(Long id) throws ProdutoNaoExisteException {
		Produto produto = buscaProduto(id);
		repository.deleteProdutoById(id);

    }
}
