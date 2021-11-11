package com.winningwomen.supermercadoYara.controller;

import com.winningwomen.supermercadoYara.exception.AmbiguidadeDeNomesCategoriasException;
import com.winningwomen.supermercadoYara.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.winningwomen.supermercadoYara.model.Categoria;
import com.winningwomen.supermercadoYara.repository.CategoriaRepository;

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
	public void cadastrarCategoria(@RequestBody @Valid Categoria categoria) throws AmbiguidadeDeNomesCategoriasException {
		categoriaService.cadastrar(categoria);
	}
}
