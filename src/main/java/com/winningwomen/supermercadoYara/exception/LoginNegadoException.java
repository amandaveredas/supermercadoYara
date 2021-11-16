package com.winningwomen.supermercadoYara.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class LoginNegadoException extends Exception {
    public LoginNegadoException() {
        super("E-mail ou senha incorretos.");
    }
}
