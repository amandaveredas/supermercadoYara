package com.winningwomen.supermercadoYara.service;

import com.winningwomen.supermercadoYara.dto.response.ProdutoResponse;
import com.winningwomen.supermercadoYara.exception.*;
import com.winningwomen.supermercadoYara.model.Categoria;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.winningwomen.supermercadoYara.dto.request.ProdutoRequest;
import com.winningwomen.supermercadoYara.model.Produto;
import com.winningwomen.supermercadoYara.repository.ProdutoRepository;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;


@Service
public class ProdutoService {
	
	private ProdutoRepository repository;
	private CategoriaService categoriaService;
	private ImagemService imagemService;
	private LoginService loginService;

	@Autowired
	public ProdutoService(ProdutoRepository repository, CategoriaService categoriaService, ImagemService imagemService, LoginService loginService) {
		this.repository = repository;
		this.categoriaService = categoriaService;
		this.imagemService = imagemService;
		this.loginService = loginService;
	}

	public void cadastrar(HttpHeaders headers, ProdutoRequest produtoRequest) throws AmbiguidadeDeNomesProdutosException, CategoriaNaoExisteException, UsuarioNaoEAdministradorException, UsuarioNaoLogadoException {
		//loginService.verificaSeTokenValidoESeAdministradorELancaExcecoes(headers);

		if(repository.existsByNome(produtoRequest.getNome()))
			throw new AmbiguidadeDeNomesProdutosException(produtoRequest.getNome());

		Categoria categoria = categoriaService.buscarPeloId(headers,produtoRequest.getIdCategoria());
		String urlImagem = imagemService.salvarImagem(produtoRequest.getImagem());

		Produto produto = Produto.builder()
				.nome(produtoRequest.getNome())
				.categoria(categoria)
				.descricao(produtoRequest.getDescricao())
				.quantidade(produtoRequest.getQuantidade())
				.precoUnitario(produtoRequest.getPrecoUnitario())
				.imagem(urlImagem)
				.build();

		repository.save(produto);
    }

	public List<ProdutoResponse> listarTodosOrdemAlfabetica(HttpHeaders headers) throws UsuarioNaoLogadoException {
		//if(!loginService.verificaSeTokenValido(headers))
			//throw new UsuarioNaoLogadoException();

		List<Produto> produtos = repository.findAllByOrderByNomeAsc();
		List<ProdutoResponse> listaProdutosResponse = new ArrayList<>();
		for(Produto p: produtos){
			ProdutoResponse produtoResponse = ProdutoResponse.builder()
					.id(p.getId())
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

	public ProdutoResponse alterar(HttpHeaders headers, Long id, ProdutoRequest produtoRequest) throws ProdutoNaoExisteException, CategoriaNaoExisteException, UsuarioNaoEAdministradorException, UsuarioNaoLogadoException, AmbiguidadeDeNomesProdutosException {
		//loginService.verificaSeTokenValidoESeAdministradorELancaExcecoes(headers);

		Produto produto = buscaProduto(id);
		Categoria categoria = categoriaService.buscarPeloId(headers ,produtoRequest.getIdCategoria());
		String urlImagem = imagemService.salvarImagem(produtoRequest.getImagem());

		if(repository.existsByNome(produtoRequest.getNome())){
			if(!Objects.equals(repository.findByNomeIgnoringCase(produtoRequest.getNome()).getId(), id))
				throw new AmbiguidadeDeNomesProdutosException(produtoRequest.getNome());
		}

		produto.setNome(produtoRequest.getNome());
		produto.setCategoria(categoria);
		produto.setDescricao(produtoRequest.getDescricao());
		produto.setQuantidade(produtoRequest.getQuantidade());
		produto.setPrecoUnitario(produtoRequest.getPrecoUnitario());
		produto.setImagem(urlImagem);

		repository.save(produto);

		ProdutoResponse produtoResponse = ProdutoResponse.builder()
				.id(produto.getId())
				.nome(produto.getNome())
				.nomeCategoria(categoria.getNome())
				.descricao(produto.getDescricao())
				.quantidade(produto.getQuantidade())
				.precoUnitario(produto.getPrecoUnitario())
				.imagem(produto.getImagem())
				.build();

		return  produtoResponse;
	}

	public void excluir(HttpHeaders headers, Long id) throws ProdutoNaoExisteException, UsuarioNaoLogadoException, UsuarioNaoEAdministradorException {
		//loginService.verificaSeTokenValidoESeAdministradorELancaExcecoes(headers);

		Produto produto = buscaProduto(id);
		repository.deleteById(id);
		imagemService.excluir(produto.getImagem());
    }

	public void exportar(HttpHeaders headers) throws UsuarioNaoLogadoException {
		//if(!loginService.verificaSeTokenValido(headers))
			//throw new UsuarioNaoLogadoException();

		String caminhoDiretorio = criaCaminhoDiretorio();
		String caminhoArquivo = criaCaminhoArquivo("produtos.xls",caminhoDiretorio);
		criaDiretorioSeNaoExistir(caminhoDiretorio);


		List<ProdutoResponse> produtos = listarTodosOrdemAlfabetica(headers);
		List<String> cabecalhos = Arrays.asList("Nome", "Categoria", "Descrição","Quantidade", "Preço", "Imagem");
		HSSFWorkbook workbook = criaPlanilha(produtos, cabecalhos);

		try{
			FileOutputStream out = new FileOutputStream(caminhoArquivo);
			workbook.write(out);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}


	}

	private HSSFWorkbook criaPlanilha(List<ProdutoResponse> produtos, List<String> cabecalhos) {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Produtos");

		int rownum = 0;
		int cellnum = 0;
		Row row = sheet.createRow(rownum++);

		for(String s: cabecalhos){
			Cell cell = row.createCell(cellnum++);
			cell.setCellValue(s);
		}

		for(ProdutoResponse p : produtos){
			row = sheet.createRow(rownum++);
			cellnum = 0;
			Cell cellNome = row.createCell(cellnum++);
			cellNome.setCellValue(p.getNome());
			Cell cellCategoria = row.createCell(cellnum++);
			cellCategoria.setCellValue(p.getNomeCategoria());
			Cell cellDescricao = row.createCell(cellnum++);
			cellDescricao.setCellValue(p.getDescricao());
			Cell cellQuantidade = row.createCell(cellnum++);
			cellQuantidade.setCellValue(p.getQuantidade());
			Cell cellPreco = row.createCell(cellnum++);
			cellPreco.setCellValue(p.getPrecoUnitario().doubleValue());
			Cell cellImagem = row.createCell(cellnum++);
			cellImagem.setCellValue(p.getImagem());
		}

		return workbook;
	}

	private void criaDiretorioSeNaoExistir(String caminhoDiretorio) {
		File file = new File(caminhoDiretorio);
		if(!file.exists())
			file.mkdirs();
	}

	private String criaCaminhoArquivo(String nomeArquivo, String diretorio){
		String separador = System.getProperty("file.separator");
		String arquivo = diretorio + separador + nomeArquivo;

		return arquivo;
	}

	private String criaCaminhoDiretorio() {
		String separador = System.getProperty("file.separator");
		final String diretorio = System.getProperty("user.home")
				+ separador + "Documentos"
				+ separador +"vilayara"
				+ separador +"exports";
		return diretorio;
	}

	private Produto buscaProduto(Long id) throws ProdutoNaoExisteException {
		if(!repository.existsById(id))
			throw new ProdutoNaoExisteException(id);
		Produto produto = repository.findById(id).get();
		return produto;
	}

    public ProdutoResponse buscarPeloId(HttpHeaders headers, Long id) throws ProdutoNaoExisteException, UsuarioNaoLogadoException {
//		if(!loginService.verificaSeTokenValido(headers))
//		throw new UsuarioNaoLogadoException();
		Produto produto = buscaProduto(id);

		ProdutoResponse produtoResponse = ProdutoResponse.builder()
				.id(produto.getId())
				.imagem(produto.getImagem())
				.precoUnitario(produto.getPrecoUnitario())
				.quantidade(produto.getQuantidade())
				.descricao(produto.getDescricao())
				.nomeCategoria(produto.getCategoria().getNome())
				.nome(produto.getNome()).build();

		return produtoResponse;
    }
}
