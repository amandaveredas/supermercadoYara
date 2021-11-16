package com.winningwomen.supermercadoYara.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AmbiguidadeDeNomesProdutosException extends Exception   {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AmbiguidadeDeNomesProdutosException(String nome) {
        super("Já existe um produto cadastrado com o nome '"+nome+"'.");
}
}
