package com.winningwomen.supermercadoYara.controller;

import com.winningwomen.supermercadoYara.dto.request.CategoriaRequest;
import com.winningwomen.supermercadoYara.exception.AmbiguidadeDeNomesCategoriasException;
import com.winningwomen.supermercadoYara.exception.UsuarioNaoEAdministradorException;
import com.winningwomen.supermercadoYara.exception.UsuarioNaoLogadoException;
import com.winningwomen.supermercadoYara.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.winningwomen.supermercadoYara.model.Categoria;

import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaController {
	

	private CategoriaService categoriaService;

	@Autowired
	public CategoriaController(CategoriaService categoriaService) {
		this.categoriaService = categoriaService;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void cadastrarCategoria(@RequestHeader HttpHeaders headers, @RequestBody @Valid CategoriaRequest categoriaRequest) throws AmbiguidadeDeNomesCategoriasException, UsuarioNaoEAdministradorException, UsuarioNaoLogadoException {
		categoriaService.cadastrar(headers,categoriaRequest);
	}
	
	@GetMapping
	public List<Categoria> listarTodasCategorias(@RequestHeader HttpHeaders headers) throws UsuarioNaoLogadoException {
		return categoriaService.listarTodosOrdemAlfabetica(headers);
	}
}
