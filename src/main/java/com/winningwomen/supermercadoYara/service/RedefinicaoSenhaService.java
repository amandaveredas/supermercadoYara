package com.winningwomen.supermercadoYara.service;

import com.winningwomen.supermercadoYara.dto.request.GeraTokenRedefineRequest;
import com.winningwomen.supermercadoYara.dto.request.RedefineSenhaRequest;
import com.winningwomen.supermercadoYara.exception.*;
import com.winningwomen.supermercadoYara.model.RedefinicaoSenha;
import com.winningwomen.supermercadoYara.model.Usuario;
import com.winningwomen.supermercadoYara.repository.RedefinicaoSenhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class RedefinicaoSenhaService {

    private UsuarioService usuarioService;
    private RedefinicaoSenhaRepository repository;
    private LoginService loginService;

    @Autowired
    public RedefinicaoSenhaService(UsuarioService usuarioService, RedefinicaoSenhaRepository repository, LoginService loginService) {
        this.usuarioService = usuarioService;
        this.repository = repository;
        this.loginService = loginService;
    }

    @Transactional
    public Long gerarTokenRecuperacao(HttpHeaders headers, GeraTokenRedefineRequest geraTokenRedefineRequest) throws UsuarioNaoEAdministradorException, UsuarioNaoLogadoException, UsuarioNaoExisteComEmailException {
       //loginService.verificaSeTokenValidoESeAdministradorELancaExcecoes(headers);
       Usuario usuario = usuarioService.buscaUsuarioPeloEmail(geraTokenRedefineRequest.getEmail());

       RedefinicaoSenha recuperacaoSenha = RedefinicaoSenha.builder()
               .horaSolicitacao(LocalDateTime.now())
               .horaExpiracao(LocalDateTime.now().plusHours(1))
               .usuario(usuario).build();

       repository.deleteAllByUsuarioEquals(usuario);
       repository.save(recuperacaoSenha);
       return recuperacaoSenha.getId();

    }

    public void recuperarSenha(RedefineSenhaRequest redefineSenhaRequest) throws UsuarioNaoExisteComEmailException, UsuarioNaoExisteException, RedefinicaoSenhaInvalidaException {
        Usuario usuario = usuarioService.buscaUsuarioPeloEmail(redefineSenhaRequest.getEmail());

        Optional<RedefinicaoSenha> recuperacaoSenha = repository.findById(redefineSenhaRequest.getToken());
        if (recuperacaoSenha.isPresent() &&
            recuperacaoSenha.get().getId() == redefineSenhaRequest.getToken() &&
            recuperacaoSenha.get().getUsuario() == usuario &&
            recuperacaoSenha.get().getHoraExpiracao().isAfter(LocalDateTime.now())){
                usuarioService.atualizarSenha(usuario.getId(), redefineSenhaRequest);

        }else throw new RedefinicaoSenhaInvalidaException();
    }
}
