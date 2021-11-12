package com.winningwomen.supermercadoYara.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.winningwomen.supermercadoYara.model.Usuario;
import com.winningwomen.supermercadoYara.repository.UsuarioRepository;

@RestController
@RequestMapping(value="/usuario")
public class UsuarioController {

	@Autowired
	UsuarioRepository usuarioRepository;
	
	@PostMapping
	public void post(@RequestBody Usuario usuario) {
		usuarioRepository.save(usuario);
	}
}
