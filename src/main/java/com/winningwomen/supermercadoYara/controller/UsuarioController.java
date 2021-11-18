package com.winningwomen.supermercadoYara.controller;

import com.winningwomen.supermercadoYara.dto.request.UsuarioRequest;
import com.winningwomen.supermercadoYara.dto.response.UsuarioResponse;
import com.winningwomen.supermercadoYara.exception.*;
import com.winningwomen.supermercadoYara.service.UsuarioService;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.winningwomen.supermercadoYara.model.Usuario;

import javax.validation.Valid;

@RestController
@RequestMapping(value="/usuarios")
public class UsuarioController {

	private UsuarioService usuarioService;

	@Autowired
	public UsuarioController(UsuarioService usuarioService){this.usuarioService = usuarioService;}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void cadastrarUsuario(@RequestHeader HttpHeaders headers, @RequestBody @Valid UsuarioRequest usuarioRequest) throws AmbiguidadeDeNomesUsuariosException, FuncaoNaoExisteException, UsuarioNaoEAdministradorException, UsuarioNaoLogadoException {
		usuarioService.cadastrarUsuario(headers,usuarioRequest);
	}

	@GetMapping
	public List<UsuarioResponse> listarTodosUsuarios(@RequestHeader HttpHeaders headers) throws UsuarioNaoEAdministradorException, UsuarioNaoLogadoException {
		return usuarioService.listarTodosOrdemAlfabetica(headers);
	}

	@PutMapping("/{id}")
	public UsuarioResponse alterarUsuario(@RequestHeader HttpHeaders headers, @PathVariable Long id, @RequestBody @Valid UsuarioRequest usuarioRequest) throws AmbiguidadeDeNomesUsuariosException, UsuarioNaoExisteException, FuncaoNaoExisteException, UsuarioNaoEAdministradorException, UsuarioNaoLogadoException {
		return usuarioService.atualizar(headers, id, usuarioRequest);
	}

	@DeleteMapping("/{id}")
	public void excluirUsuario(@RequestHeader HttpHeaders headers, @PathVariable Long id) throws UsuarioNaoExisteException, UsuarioNaoEAdministradorException, UsuarioNaoLogadoException {
		usuarioService.excluir(headers,id);
	}

}
