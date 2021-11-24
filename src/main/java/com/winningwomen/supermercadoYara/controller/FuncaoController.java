package com.winningwomen.supermercadoYara.controller;

import java.util.List;

import com.winningwomen.supermercadoYara.exception.*;
import com.winningwomen.supermercadoYara.model.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.winningwomen.supermercadoYara.dto.request.FuncaoRequest;
import com.winningwomen.supermercadoYara.model.Funcao;
import com.winningwomen.supermercadoYara.service.FuncaoService;

import javax.validation.Valid;

@RestController
@RequestMapping(value="/funcao")
public class FuncaoController {
	
	@Autowired
	FuncaoService funcaoService; 
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void post(@RequestHeader HttpHeaders headers, @RequestBody @Valid FuncaoRequest funcaoRequest) throws UsuarioNaoEAdministradorException, UsuarioNaoLogadoException, FuncaoJaExisteException {
		funcaoService.cadastrar(headers, funcaoRequest);
	}

	@GetMapping
	public List<Funcao> listarTodasFuncoes(@RequestHeader HttpHeaders headers) throws UsuarioNaoEAdministradorException, UsuarioNaoLogadoException {
		return funcaoService.listarTodosOrdemAlfabetica(headers);
	}

	@GetMapping("/{id}")
	public Funcao buscarFuncao(@RequestHeader HttpHeaders headers, @PathVariable Long id) throws FuncaoNaoExisteIdException, UsuarioNaoEAdministradorException, UsuarioNaoLogadoException {
		return funcaoService.buscarPeloId(headers, id);
	}
}
