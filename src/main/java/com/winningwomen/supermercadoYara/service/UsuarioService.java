package com.winningwomen.supermercadoYara.service;

import com.winningwomen.supermercadoYara.dto.request.UsuarioRequest;
import com.winningwomen.supermercadoYara.dto.response.UsuarioResponse;
import com.winningwomen.supermercadoYara.exception.AmbiguidadeDeNomesUsuariosException;
import com.winningwomen.supermercadoYara.model.Usuario;
import com.winningwomen.supermercadoYara.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {

    private UsuarioRepository repository;


    @Autowired
    public UsuarioService(UsuarioRepository repository){
        this.repository = repository;
    }

    public void cadastrar(UsuarioRequest usuarioRequest) throws AmbiguidadeDeNomesUsuariosException{

        if(repository.existsByNome(usuarioRequest.getUser_name())) throw new AmbiguidadeDeNomesUsuariosException(usuarioRequest.getUser_name());

        Usuario usuario = Usuario.builder()
                .user_name(usuarioRequest.getUser_name())
                .nome(usuarioRequest.getNome())
                .sobrenome(usuarioRequest.getSobrenome())
                .email(usuarioRequest.getEmail())
                .senha(usuarioRequest.getSenha())
                .data_criacao(usuarioRequest.getData_criacao())
                .build();

        repository.save(usuario);
    }

    public List<UsuarioResponse> listarTodos(){
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
                    .build();
            listaUsuariosResponse.add(usuarioResponse);
        }

        return listaUsuariosResponse;
    }

    public void atualizar(UsuarioRequest usuarioRequest) throws AmbiguidadeDeNomesUsuariosException{

        if(repository.existsByNome(usuarioRequest.getUser_name())) throw new AmbiguidadeDeNomesUsuariosException(usuarioRequest.getUser_name());

        Usuario usuario = Usuario.builder()
                .user_name(usuarioRequest.getUser_name())
                .nome(usuarioRequest.getNome())
                .sobrenome(usuarioRequest.getSobrenome())
                .email(usuarioRequest.getEmail())
                .senha(usuarioRequest.getSenha())
                .data_criacao(usuarioRequest.getData_criacao())
                .build();

        repository.save(usuario);
    }

    public void deletar(UsuarioRequest usuarioRequest){
        if (repository.existsById(usuarioRequest.getId())) {
            Usuario usuario = new Usuario();
            repository.delete(usuario);
        }
    }

}
