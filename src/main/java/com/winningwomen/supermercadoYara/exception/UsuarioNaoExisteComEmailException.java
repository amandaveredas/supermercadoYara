package com.winningwomen.supermercadoYara.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UsuarioNaoExisteComEmailException extends Exception {
    public UsuarioNaoExisteComEmailException(String email) {
        super("O usuário com email '"+email+"' não foi encontrado.");
    }
}
