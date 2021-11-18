package com.winningwomen.supermercadoYara.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UsuarioNaoExisteException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UsuarioNaoExisteException(Long id) {
		super("O usuário com id '"+id+"' não foi encontrado.");
	}
}
