package com.winningwomen.supermercadoYara.service;

import com.winningwomen.supermercadoYara.exception.LoginNegadoException;
import com.winningwomen.supermercadoYara.model.Usuario;
import com.winningwomen.supermercadoYara.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerificarEmailESenhaService {

    private UsuarioRepository repository;

    @Autowired
    public VerificarEmailESenhaService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Usuario verificarEmailESenha(String email, String senha) throws LoginNegadoException {
        Usuario usuario = repository.findByEmailEqualsAndSenhaEquals(email,senha);
        if(usuario == null)
            throw new LoginNegadoException();
        return usuario;
    }
}
