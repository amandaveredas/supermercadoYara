package com.winningwomen.supermercadoYara.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winningwomen.supermercadoYara.dto.request.UsuarioRequest;
import com.winningwomen.supermercadoYara.dto.response.UsuarioResponse;
import com.winningwomen.supermercadoYara.model.Funcao;
import com.winningwomen.supermercadoYara.model.Usuario;
import com.winningwomen.supermercadoYara.repository.FuncaoRepository;
import com.winningwomen.supermercadoYara.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	FuncaoRepository funcaoRepository;
	
	public UsuarioResponse cadastrar(UsuarioRequest usuarioRequest) {
		Funcao funcao = this.funcaoRepository.findById(usuarioRequest.getIdFuncao()).orElseThrow(RuntimeException::new);
		Usuario usuario = new Usuario(usuarioRequest, funcao);
		Usuario usuarioCompleto = this.usuarioRepository.save(usuario);
		return new UsuarioResponse(usuarioCompleto);
	}
	
	public List<Usuario> listarTodosOrdemAlfabetica(){
		return usuarioRepository.findAllByOrderByNomeAsc();
	}
}
