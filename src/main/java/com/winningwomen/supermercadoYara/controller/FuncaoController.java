package com.winningwomen.supermercadoYara.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.winningwomen.supermercadoYara.model.Funcao;
import com.winningwomen.supermercadoYara.repository.FuncaoRepository;

@RestController
@RequestMapping(value="/funcao")
public class FuncaoController {
	
	@Autowired
	FuncaoRepository funcaoRepository;
	
	@PostMapping(consumes = "application/json", produces = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public void post(@RequestBody Funcao funcao) {
		funcaoRepository.save(funcao);
	}

}
