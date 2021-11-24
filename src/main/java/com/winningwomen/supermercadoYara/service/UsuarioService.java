package com.winningwomen.supermercadoYara.service;

import com.winningwomen.supermercadoYara.dto.request.RedefineSenhaRequest;
import com.winningwomen.supermercadoYara.dto.request.UsuarioRequest;
import com.winningwomen.supermercadoYara.dto.response.UsuarioResponse;
import com.winningwomen.supermercadoYara.exception.*;
import com.winningwomen.supermercadoYara.model.Funcao;
import com.winningwomen.supermercadoYara.model.Usuario;
import com.winningwomen.supermercadoYara.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UsuarioService {

    private UsuarioRepository repository;
    private FuncaoService funcaoService;
    private LoginService loginService;
    
    @Autowired
    public UsuarioService(UsuarioRepository repository, FuncaoService funcaoService, LoginService loginService){
        this.repository = repository;
        this.funcaoService = funcaoService;
        this.loginService = loginService;
    }
    
    
    public void cadastrarUsuario(HttpHeaders headers,UsuarioRequest usuarioRequest) throws AmbiguidadeDeNomesUsuariosException, FuncaoNaoExisteException, UsuarioNaoEAdministradorException, UsuarioNaoLogadoException, AmbiguidadeDeEmailsException {
        //loginService.verificaSeTokenValidoESeAdministradorELancaExcecoes(headers);
        if(repository.existsByUserName(usuarioRequest.getUserName()))
        	throw new AmbiguidadeDeNomesUsuariosException(usuarioRequest.getUserName());

        if(repository.existsByEmail(usuarioRequest.getEmail()))
            throw new AmbiguidadeDeEmailsException(usuarioRequest.getEmail());
        
        Funcao funcao = funcaoService.buscarPeloNome(usuarioRequest.nomeFuncao());
        
        Usuario usuario = Usuario.builder()
                .userName(usuarioRequest.getUserName())
                .funcao(funcao)
                .nome(usuarioRequest.getNome())
                .sobrenome(usuarioRequest.getSobrenome())
                .email(usuarioRequest.getEmail())
                .senha(usuarioRequest.getSenha())
                .data_criacao(LocalDateTime.now())
                .build();

        repository.save(usuario);
    }

    
    public List<UsuarioResponse> listarTodosOrdemAlfabetica(HttpHeaders headers) throws UsuarioNaoEAdministradorException, UsuarioNaoLogadoException {
        //loginService.verificaSeTokenValidoESeAdministradorELancaExcecoes(headers);
        List<Usuario> usuarios = repository.findAll();
        List<UsuarioResponse> listaUsuariosResponse = new ArrayList<>();

        for (Usuario u: usuarios){
            UsuarioResponse usuarioResponse = UsuarioResponse.builder()
                    .id(u.getId())
                    .userName(u.getUserName())
                    .nome(u.getNome())
                    .sobrenome(u.getSobrenome())
                    .email(u.getEmail())
                    .senha(u.getSenha())
                    .dataCriacao(u.getData_criacao())
                    .nomeFuncao(u.getFuncao().getNome())
                    .build();
            listaUsuariosResponse.add(usuarioResponse);
        }

        return listaUsuariosResponse;
    }

    
    public UsuarioResponse atualizar(HttpHeaders headers, Long id, UsuarioRequest usuarioRequest) throws AmbiguidadeDeNomesUsuariosException, UsuarioNaoExisteException, FuncaoNaoExisteException, UsuarioNaoEAdministradorException, UsuarioNaoLogadoException, AmbiguidadeDeEmailsException {
    	//loginService.verificaSeTokenValidoESeAdministradorELancaExcecoes(headers);
        Usuario usuario = buscaUsuario(id);
    	Funcao funcao = funcaoService.buscarPeloNome(usuarioRequest.nomeFuncao());

        if(repository.existsByUserName(usuarioRequest.getUserName())){
            if(!Objects.equals(repository.findByUserName(usuarioRequest.getUserName()).getId(), id))
                throw new AmbiguidadeDeNomesUsuariosException(usuarioRequest.getUserName());
        }

        if(repository.existsByEmail(usuarioRequest.getEmail())){
            if(!Objects.equals(repository.findByEmailEquals(usuarioRequest.getEmail()).getId(), id))
                throw new AmbiguidadeDeEmailsException(usuarioRequest.getEmail());
        }
    	
    	usuario.setNome(usuarioRequest.getNome());
    	usuario.setFuncao(funcao);
    	usuario.setEmail(usuarioRequest.getEmail());
    	usuario.setSobrenome(usuarioRequest.getSobrenome());
    	usuario.setSenha(usuarioRequest.getSenha());
    	usuario.setUserName(usuarioRequest.getUserName());
    	
    	repository.save(usuario);
    	
    	UsuarioResponse usuarioResponse = UsuarioResponse.builder()
                .id(usuario.getId())
    			.nome(usuario.getNome())
    			.nomeFuncao(funcao.getNome())
    			.email(usuario.getEmail())
    			.sobrenome(usuario.getSobrenome())
    			.senha(usuario.getSenha())
    			.dataCriacao(usuario.getData_criacao())
    			.userName(usuario.getUserName())
    			.build();
    	
    	return usuarioResponse;    	
    }

    
    public void excluir(HttpHeaders headers,Long id) throws UsuarioNaoExisteException, UsuarioNaoEAdministradorException, UsuarioNaoLogadoException {
        loginService.verificaSeTokenValidoESeAdministradorELancaExcecoes(headers);
        if(!repository.existsById(id))
            throw new UsuarioNaoExisteException(id);
         repository.deleteById(id);
    }

    
    public Usuario buscaUsuarioPeloEmail(String email) throws UsuarioNaoExisteComEmailException {
        if(!repository.existsByEmail(email))
            throw new UsuarioNaoExisteComEmailException(email);

            return repository.findByEmailEquals(email);
    }
    
    
    public Usuario buscaUsuario(Long id) throws UsuarioNaoExisteException {
        if(!repository.existsById(id)) throw new UsuarioNaoExisteException(id);
        Usuario usuario = repository.findById(id).get();
        return usuario;
    }

    
    public void atualizarSenha(Long id, RedefineSenhaRequest redefineSenhaRequest) throws UsuarioNaoExisteException {
        Usuario usuario = buscaUsuario(id);
        usuario.setSenha(redefineSenhaRequest.getNovaSenha());

        repository.save(usuario);

    }


    
}



