package com.winningwomen.supermercadoYara.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FuncaoJaExisteException extends Exception {
    public FuncaoJaExisteException(String funcaoNome) {
        super("Já existe uma função com o nome '"+funcaoNome+"'.");
    }
}
