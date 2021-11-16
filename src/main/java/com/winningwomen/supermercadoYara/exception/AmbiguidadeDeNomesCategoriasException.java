package com.winningwomen.supermercadoYara.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AmbiguidadeDeNomesCategoriasException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AmbiguidadeDeNomesCategoriasException(String nome) {
        super("Já existe uma categoria cadastrada com o nome '"+nome+"'.");
    }
}
