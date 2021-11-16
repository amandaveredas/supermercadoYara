package com.winningwomen.supermercadoYara.controller;

import com.winningwomen.supermercadoYara.dto.request.LoginUsuarioRequest;
import com.winningwomen.supermercadoYara.exception.LoginNegadoException;
import com.winningwomen.supermercadoYara.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/login")
public class LoginController {

    private LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping
    public Long logarUsuario(@RequestBody @Valid LoginUsuarioRequest loginUsuarioRequest) throws LoginNegadoException {
        return loginService.gerarTokenLogin(loginUsuarioRequest);
    }
}
