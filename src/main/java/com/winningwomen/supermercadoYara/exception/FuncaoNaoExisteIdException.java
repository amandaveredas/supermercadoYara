package com.winningwomen.supermercadoYara.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FuncaoNaoExisteIdException extends Exception {
    public FuncaoNaoExisteIdException(Long id) {
        super("Nenhuma função com id '"+id+"' foi encontrada.");
    }
}
