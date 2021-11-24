package com.winningwomen.supermercadoYara.service;

import java.util.List;

import com.winningwomen.supermercadoYara.exception.*;
import com.winningwomen.supermercadoYara.model.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.winningwomen.supermercadoYara.dto.request.FuncaoRequest;
import com.winningwomen.supermercadoYara.model.Funcao;
import com.winningwomen.supermercadoYara.repository.FuncaoRepository;

@Service
public class FuncaoService {
	private FuncaoRepository repository;
	private LoginService loginService;

	@Autowired
	public FuncaoService(FuncaoRepository funcaoRepository, LoginService loginService) {
		this.repository = funcaoRepository;
		this.loginService = loginService;
	}
	
	public Funcao buscarPeloNome(String nomeFuncao) throws FuncaoNaoExisteException{
		if(!repository.existsByNomeIgnoringCase(nomeFuncao)) throw new FuncaoNaoExisteException(nomeFuncao);
		return repository.findByNomeIgnoringCase(nomeFuncao);
	}
	
	public void cadastrar(HttpHeaders headers, FuncaoRequest funcaoRequest) throws UsuarioNaoEAdministradorException, UsuarioNaoLogadoException, FuncaoJaExisteException {
		//loginService.verificaSeTokenValidoESeAdministradorELancaExcecoes(headers);
		if(repository.existsByNomeIgnoringCase(funcaoRequest.getNome()))
			throw new FuncaoJaExisteException(funcaoRequest.getNome());
		Funcao funcao = new Funcao(funcaoRequest);
		repository.save(funcao);


	}
	
	public List<Funcao> listarTodosOrdemAlfabetica(HttpHeaders headers) throws UsuarioNaoEAdministradorException, UsuarioNaoLogadoException {
		//loginService.verificaSeTokenValidoESeAdministradorELancaExcecoes(headers);

		return repository.findAllByOrderByNomeAsc();
	}

    public Funcao buscarPeloId(HttpHeaders headers, Long id) throws UsuarioNaoEAdministradorException, UsuarioNaoLogadoException, FuncaoNaoExisteIdException {
		//loginService.verificaSeTokenValidoESeAdministradorELancaExcecoes(headers);
		if(!repository.existsById(id)) throw new FuncaoNaoExisteIdException(id);
		return repository.findById(id).get();
    }
}
