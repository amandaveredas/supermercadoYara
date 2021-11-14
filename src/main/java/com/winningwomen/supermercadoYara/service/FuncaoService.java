package com.winningwomen.supermercadoYara.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winningwomen.supermercadoYara.dto.request.FuncaoRequest;
import com.winningwomen.supermercadoYara.model.Funcao;
import com.winningwomen.supermercadoYara.repository.FuncaoRepository;

@Service
public class FuncaoService {

	@Autowired
	FuncaoRepository funcaoRepository;

	public void cadastrar(FuncaoRequest funcaoRequest) {
		Funcao funcao = new Funcao(funcaoRequest);
		funcaoRepository.save(funcao);
	}
	
	public List<Funcao> listarTodosOrdemAlfabetica(){
		return funcaoRepository.findAllByOrderByNomeAsc();
	}
}
