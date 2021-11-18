package com.winningwomen.supermercadoYara.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FuncaoNaoExisteException extends Exception {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FuncaoNaoExisteException(String nome) {
		super("Nenhuma função com nome '"+nome+"' foi encontrada.");
	}

}
