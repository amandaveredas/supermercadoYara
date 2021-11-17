package com.winningwomen.supermercadoYara.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UsuarioNaoEAdministradorException extends Exception {
    public UsuarioNaoEAdministradorException() {
        super("Acesso Negado! Usuário não possui acesso a essa funcionalidade.");
    }
}
