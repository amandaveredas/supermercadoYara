package com.winningwomen.supermercadoYara.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AmbiguidadeDeNomesProdutosException extends Exception   {
    public AmbiguidadeDeNomesProdutosException(String nome) {
        super("Já existe um produto cadastrado com o nome '"+nome+"'.");
}
}
