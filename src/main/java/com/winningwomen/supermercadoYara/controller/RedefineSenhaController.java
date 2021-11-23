package com.winningwomen.supermercadoYara.controller;

import com.winningwomen.supermercadoYara.dto.request.GeraTokenRedefineRequest;
import com.winningwomen.supermercadoYara.dto.request.RedefineSenhaRequest;
import com.winningwomen.supermercadoYara.exception.*;
import com.winningwomen.supermercadoYara.service.RedefinicaoSenhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/redefinicao")
public class RedefineSenhaController {

    private RedefinicaoSenhaService service;

    @Autowired
    public RedefineSenhaController(RedefinicaoSenhaService service) {
        this.service = service;
    }

    @PostMapping("/token")
    public Long gerarTokenRecuperarSenha(@RequestHeader HttpHeaders headers, @RequestBody @Valid GeraTokenRedefineRequest geraTokenRedefineRequest) throws UsuarioNaoEAdministradorException, UsuarioNaoLogadoException, UsuarioNaoExisteComEmailException {
        return service.gerarTokenRecuperacao(headers, geraTokenRedefineRequest);
    }

    @PostMapping
    public void recuperarSenha(@RequestBody @Valid RedefineSenhaRequest redefineSenhaRequest) throws UsuarioNaoExisteException, RedefinicaoSenhaInvalidaException, UsuarioNaoExisteComEmailException {
        service.recuperarSenha(redefineSenhaRequest);
    }


}
