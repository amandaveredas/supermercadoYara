package com.winningwomen.supermercadoYara.controller;

import com.winningwomen.supermercadoYara.dto.request.UsuarioRequest;
import com.winningwomen.supermercadoYara.dto.response.UsuarioResponse;
import com.winningwomen.supermercadoYara.exception.AmbiguidadeDeNomesUsuariosException;
import com.winningwomen.supermercadoYara.exception.FuncaoNaoExisteException;
import com.winningwomen.supermercadoYara.exception.UsuarioNaoExisteException;
import com.winningwomen.supermercadoYara.service.UsuarioService;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.winningwomen.supermercadoYara.model.Usuario;

import javax.validation.Valid;

@RestController
@RequestMapping(value="/usuario")
public class UsuarioController {

	private UsuarioService usuarioService;

	@Autowired
	public UsuarioController(UsuarioService usuarioService){this.usuarioService = usuarioService;}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void cadastrarUsuario(@RequestBody @Valid UsuarioRequest usuarioRequest) throws AmbiguidadeDeNomesUsuariosException, FuncaoNaoExisteException{
		usuarioService.cadastrarUsuario(usuarioRequest);
	}

	@GetMapping
	public List<UsuarioResponse> listarTodosUsuarios(){
		return usuarioService.listarTodosOrdemAlfabetica();
	}

	@PutMapping("/{id}")
	public UsuarioResponse alterarUsuario(@PathVariable Long id, @RequestBody @Valid UsuarioRequest usuarioRequest) throws AmbiguidadeDeNomesUsuariosException, UsuarioNaoExisteException, FuncaoNaoExisteException{
		return usuarioService.atualizar(id, usuarioRequest);
	}

	@DeleteMapping("/{id}")
	public void excluirUsuario(@PathVariable Long id) throws UsuarioNaoExisteException{
		usuarioService.excluir(id);
	}

}
