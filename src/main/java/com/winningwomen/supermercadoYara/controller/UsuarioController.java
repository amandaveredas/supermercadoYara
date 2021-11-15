package com.winningwomen.supermercadoYara.controller;

import com.winningwomen.supermercadoYara.dto.request.UsuarioRequest;
import com.winningwomen.supermercadoYara.dto.response.UsuarioResponse;
import com.winningwomen.supermercadoYara.exception.AmbiguidadeDeNomesUsuariosException;
import com.winningwomen.supermercadoYara.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.winningwomen.supermercadoYara.model.Usuario;
import com.winningwomen.supermercadoYara.repository.UsuarioRepository;

import javax.persistence.Id;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value="/usuario")
public class UsuarioController {

	private UsuarioService usuarioService;

	@Autowired
	public UsuarioController(UsuarioService usuarioService){this.usuarioService = usuarioService;}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void cadastrarUsuario(@RequestBody @Valid UsuarioRequest usuarioRequest) throws AmbiguidadeDeNomesUsuariosException{
		usuarioService.cadastrar(usuarioRequest);
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<UsuarioResponse> listarTodosUsuarios(){
		return usuarioService.listarTodos();
	}

	@PutMapping
	@ResponseStatus(HttpStatus.OK)
	public void atualizarUsuario(@RequestBody @Valid UsuarioRequest usuarioRequest)throws AmbiguidadeDeNomesUsuariosException{
		usuarioService.atualizar(usuarioRequest);
	}

	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(){
		UsuarioRequest usuarioRequest = new UsuarioRequest();
		usuarioService.deletar(usuarioRequest);
	}

}
