package com.winningwomen.supermercadoYara.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UsuarioNaoLogadoExcetion extends Exception {
    public UsuarioNaoLogadoExcetion() {
        super("Acesso negado. Usuário precisa logar no sistema.");
    }
}
