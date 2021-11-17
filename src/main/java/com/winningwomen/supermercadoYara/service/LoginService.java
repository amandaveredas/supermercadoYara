package com.winningwomen.supermercadoYara.service;

import com.winningwomen.supermercadoYara.dto.request.LoginUsuarioRequest;
import com.winningwomen.supermercadoYara.exception.LoginNegadoException;
import com.winningwomen.supermercadoYara.exception.UsuarioNaoEAdministradorException;
import com.winningwomen.supermercadoYara.exception.UsuarioNaoLogadoException;
import com.winningwomen.supermercadoYara.model.Login;
import com.winningwomen.supermercadoYara.model.Usuario;
import com.winningwomen.supermercadoYara.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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

    public boolean verificaSeTokenValido(HttpHeaders headers){
        if (headers.get("token") == null || headers.get("token").isEmpty() || headers.get("token").get(0).equalsIgnoreCase(""))
            return false;
        Long token = Long.parseLong(headers.get("token").get(0));

        if (!repository.existsById(token)){
            return false;
        }else {
            LocalDateTime dataLogout = repository.findById(token).get().getHoraLogout();
            if (dataLogout == null)
                return true;
            else return false;
        }
    }

    public boolean verificaSeAdministrador(HttpHeaders headers){
        Long token = Long.parseLong(headers.get("token").get(0));
        Login login = repository.findById(token).get();

        if(login.getUsuario().getFuncao().getNome().equalsIgnoreCase("administrador"))
            return true;
        else return false;

    }

    public void verificaSeTokenValidoESeAdministradorELancaExcecoes(HttpHeaders headers) throws UsuarioNaoLogadoException, UsuarioNaoEAdministradorException {
        if(!verificaSeTokenValido(headers))
            throw new UsuarioNaoLogadoException();

        if (!verificaSeAdministrador(headers))
            throw new UsuarioNaoEAdministradorException();
    }


}






