package com.winningwomen.supermercadoYara.service;

import java.util.List;

import com.winningwomen.supermercadoYara.exception.UsuarioNaoEAdministradorException;
import com.winningwomen.supermercadoYara.exception.UsuarioNaoLogadoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.winningwomen.supermercadoYara.dto.request.FuncaoRequest;
import com.winningwomen.supermercadoYara.exception.FuncaoNaoExisteException;
import com.winningwomen.supermercadoYara.model.Funcao;
import com.winningwomen.supermercadoYara.repository.FuncaoRepository;
import org.springframework.web.bind.annotation.RequestHeader;

@Service
public class FuncaoService {
	private FuncaoRepository funcaoRepository;
	private LoginService loginService;

	@Autowired
	public FuncaoService(FuncaoRepository funcaoRepository, LoginService loginService) {
		this.funcaoRepository= funcaoRepository;
		this.loginService = loginService;
	}
	
	public Funcao buscarPeloNome(String nomeFuncao) throws FuncaoNaoExisteException{
		if(!funcaoRepository.existsByNomeIgnoringCase(nomeFuncao)) throw new FuncaoNaoExisteException(nomeFuncao);
		return funcaoRepository.findByNomeIgnoringCase(nomeFuncao);
	}
	
	public void cadastrar(HttpHeaders headers, FuncaoRequest funcaoRequest) throws UsuarioNaoEAdministradorException, UsuarioNaoLogadoException {
		//loginService.verificaSeTokenValidoESeAdministradorELancaExcecoes(headers);
		Funcao funcao = new Funcao(funcaoRequest);
		funcaoRepository.save(funcao);
	}
	
	public List<Funcao> listarTodosOrdemAlfabetica(HttpHeaders headers) throws UsuarioNaoEAdministradorException, UsuarioNaoLogadoException {
		loginService.verificaSeTokenValidoESeAdministradorELancaExcecoes(headers);

		return funcaoRepository.findAllByOrderByNomeAsc();
	}
}
