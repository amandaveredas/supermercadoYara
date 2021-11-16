package com.winningwomen.supermercadoYara.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AmbiguidadeDeNomesUsuariosException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AmbiguidadeDeNomesUsuariosException(String user_name){
        super("Já existe um usuario cadastrado com o nome '"+user_name+"'.");
    }
}
