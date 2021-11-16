package com.winningwomen.supermercadoYara.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winningwomen.supermercadoYara.dto.request.FuncaoRequest;
import com.winningwomen.supermercadoYara.exception.FuncaoNaoExisteException;
import com.winningwomen.supermercadoYara.model.Funcao;
import com.winningwomen.supermercadoYara.repository.FuncaoRepository;

@Service
public class FuncaoService {
	private FuncaoRepository funcaoRepository;

	@Autowired
	public FuncaoService(FuncaoRepository funcaoRepository) {
		this.funcaoRepository= funcaoRepository;
	}
	
	public Funcao buscarPeloId(long idFuncao) throws FuncaoNaoExisteException{
		if(!funcaoRepository.existsById(idFuncao)) throw new FuncaoNaoExisteException(idFuncao);
		return funcaoRepository.findById(idFuncao).get();
	}
	
	public void cadastrar(FuncaoRequest funcaoRequest) {
		Funcao funcao = new Funcao(funcaoRequest);
		funcaoRepository.save(funcao);
	}
	
	public List<Funcao> listarTodosOrdemAlfabetica(){
		return funcaoRepository.findAllByOrderByNomeAsc();
	}
}
