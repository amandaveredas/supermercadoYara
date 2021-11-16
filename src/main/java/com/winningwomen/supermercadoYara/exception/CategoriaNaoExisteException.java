package com.winningwomen.supermercadoYara.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CategoriaNaoExisteException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CategoriaNaoExisteException(Long id) {
        super("Nenhuma categoria com id '"+id+"' foi encontrada.");
    }
}
