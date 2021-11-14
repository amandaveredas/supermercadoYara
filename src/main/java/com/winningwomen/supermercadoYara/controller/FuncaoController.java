package com.winningwomen.supermercadoYara.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.winningwomen.supermercadoYara.dto.request.FuncaoRequest;
import com.winningwomen.supermercadoYara.model.Funcao;
import com.winningwomen.supermercadoYara.service.FuncaoService;

@RestController
@RequestMapping(value="/funcao")
public class FuncaoController {
	
	@Autowired
	FuncaoService funcaoService; 
	
	@PostMapping(consumes = "application/json", produces = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public void post(@RequestBody FuncaoRequest funcaoRequest) {
		funcaoService.cadastrar(funcaoRequest);
	}

	@GetMapping
	public List<Funcao> listarTodasFuncoes(){
		return funcaoService.listarTodosOrdemAlfabetica();
	}
}
