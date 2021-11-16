package com.winningwomen.supermercadoYara.service;

import com.winningwomen.supermercadoYara.dto.request.UsuarioRequest;
import com.winningwomen.supermercadoYara.dto.response.UsuarioResponse;
import com.winningwomen.supermercadoYara.exception.AmbiguidadeDeNomesUsuariosException;
import com.winningwomen.supermercadoYara.exception.FuncaoNaoExisteException;
import com.winningwomen.supermercadoYara.exception.UsuarioNaoExisteException;
import com.winningwomen.supermercadoYara.model.Funcao;
import com.winningwomen.supermercadoYara.model.Usuario;
import com.winningwomen.supermercadoYara.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {

    private UsuarioRepository repository;
    private FuncaoService funcaoService;
    
    @Autowired
    public UsuarioService(UsuarioRepository repository, FuncaoService funcaoService){
        this.repository = repository;
        this.funcaoService = funcaoService;
    }
    
    
    public void cadastrarUsuario(UsuarioRequest usuarioRequest) throws AmbiguidadeDeNomesUsuariosException, FuncaoNaoExisteException{

        if(repository.existsByNome(usuarioRequest.getUser_name())) 
        	throw new AmbiguidadeDeNomesUsuariosException(usuarioRequest.getNome());
        
        Funcao funcao = funcaoService.buscarPeloId(usuarioRequest.getIdFuncao());
        
        Usuario usuario = Usuario.builder()
                .user_name(usuarioRequest.getUser_name())
                .funcao(funcao)
                .nome(usuarioRequest.getNome())
                .sobrenome(usuarioRequest.getSobrenome())
                .email(usuarioRequest.getEmail())
                .senha(usuarioRequest.getSenha())
                .data_criacao(usuarioRequest.getData_criacao())
                .build();

        repository.save(usuario);
    }

    public List<UsuarioResponse> listarTodosOrdemAlfabetica(){
        List<Usuario> usuarios = repository.findAll();
        List<UsuarioResponse> listaUsuariosResponse = new ArrayList<>();
        for (Usuario u: usuarios){
            UsuarioResponse usuarioResponse = UsuarioResponse.builder()
                    .user_name(u.getUser_name())
                    .nome(u.getNome())
                    .sobrenome(u.getSobrenome())
                    .email(u.getEmail())
                    .senha(u.getSenha())
                    .data_criacao(u.getData_criacao())
                    .nomeFuncao(u.getFuncao().getNome())
                    .build();
            listaUsuariosResponse.add(usuarioResponse);
        }

        return listaUsuariosResponse;
    }

    public UsuarioResponse atualizar(Long id, UsuarioRequest usuarioRequest) throws AmbiguidadeDeNomesUsuariosException, UsuarioNaoExisteException, FuncaoNaoExisteException{
    	Usuario usuario = buscaUsuario(id);
    	Funcao funcao = funcaoService.buscarPeloId(usuarioRequest.getIdFuncao());
    	
    	usuario.setNome(usuarioRequest.getNome());
    	usuario.setFuncao(funcao);
    	usuario.setEmail(usuarioRequest.getEmail());
    	usuario.setSobrenome(usuarioRequest.getSobrenome());
    	usuario.setSenha(usuarioRequest.getSenha());
    	usuario.setData_criacao(usuarioRequest.getData_criacao());
    	usuario.setUser_name(usuarioRequest.getUser_name());
    	
    	repository.save(usuario);
    	
    	UsuarioResponse usuarioResponse = UsuarioResponse.builder()
    			.nome(usuario.getNome())
    			.nomeFuncao(funcao.getNome())
    			.email(usuario.getEmail())
    			.sobrenome(usuario.getSobrenome())
    			.senha(usuario.getSenha())
    			.data_criacao(usuario.getData_criacao())
    			.user_name(usuario.getUser_name())
    			.build();
    	
    	return usuarioResponse;    	
    }

    private Usuario buscaUsuario(Long id) throws UsuarioNaoExisteException {
    	if(!repository.existsById(id)) throw new UsuarioNaoExisteException(id);
    	Usuario usuario = repository.findById(id).get();
    	return usuario;
    }
    
    public void excluir(Long id) throws UsuarioNaoExisteException {
         repository.deleteById(id);
    }

    
    }



