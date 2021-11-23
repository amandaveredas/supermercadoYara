package com.winningwomen.supermercadoYara.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RedefinicaoSenhaInvalidaException extends Exception {
    public RedefinicaoSenhaInvalidaException() {
        super("Não foi possível criar nova senha!");
    }
}
