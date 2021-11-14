package com.winningwomen.supermercadoYara.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.winningwomen.supermercadoYara.dto.request.UsuarioRequest;
import com.winningwomen.supermercadoYara.model.Usuario;
import com.winningwomen.supermercadoYara.service.UsuarioService;

@RestController
@RequestMapping(value="/usuario")
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void post(@RequestBody UsuarioRequest usuarioRequest) {
		this.usuarioService.cadastrar(usuarioRequest);
	}
	
	@GetMapping
	public List<Usuario> listarTodosUsuarios(){
		return usuarioService.listarTodosOrdemAlfabetica();
		
	}
}
