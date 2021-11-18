package com.winningwomen.supermercadoYara.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AmbiguidadeDeEmailsException extends Exception {
    public AmbiguidadeDeEmailsException (String email) {
        super("Já existe um usuario cadastrado com o email: " + email);
    }
}
