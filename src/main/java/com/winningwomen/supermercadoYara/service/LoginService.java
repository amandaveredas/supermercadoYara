package com.winningwomen.supermercadoYara.service;

import com.winningwomen.supermercadoYara.dto.request.LoginUsuarioRequest;
import com.winningwomen.supermercadoYara.exception.LoginNegadoException;
import com.winningwomen.supermercadoYara.model.Login;
import com.winningwomen.supermercadoYara.model.Usuario;
import com.winningwomen.supermercadoYara.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LoginService {

    private LoginRepository repository;
    private VerificarEmailESenhaService verificarEmailESenha;

    @Autowired
    public LoginService(LoginRepository repository, VerificarEmailESenhaService verificarEmailESenha) {
        this.repository = repository;
        this.verificarEmailESenha = verificarEmailESenha;
    }

    public Long gerarTokenLogin(LoginUsuarioRequest loginUsuarioRequest) throws LoginNegadoException {
        Usuario usuarioAutenticado = verificarEmailESenha.verificarEmailESenha(loginUsuarioRequest.getEmail(), loginUsuarioRequest.getSenha());
        Login login = Login.builder()
                .usuario(usuarioAutenticado)
                .horaLogin(LocalDateTime.now())
                .build();

        Login loginSalvo =  repository.save(login);
        return loginSalvo.getId();
    }

    public boolean tokenValido(Long token){
        if (!repository.existsById(token)){
            return false;
        }else {
            LocalDateTime dataLogout = repository.findById(token).get().getHoraLogout();
            if (dataLogout == null)
                return true;
            else return false;
        }
    }


}

