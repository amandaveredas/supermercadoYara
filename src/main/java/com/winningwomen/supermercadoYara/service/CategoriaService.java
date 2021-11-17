package com.winningwomen.supermercadoYara.service;

import com.winningwomen.supermercadoYara.dto.request.CategoriaRequest;
import com.winningwomen.supermercadoYara.exception.AmbiguidadeDeNomesCategoriasException;
import com.winningwomen.supermercadoYara.exception.CategoriaNaoExisteException;
import com.winningwomen.supermercadoYara.exception.UsuarioNaoEAdministradorException;
import com.winningwomen.supermercadoYara.exception.UsuarioNaoLogadoException;
import com.winningwomen.supermercadoYara.model.Categoria;
import com.winningwomen.supermercadoYara.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {
    private CategoriaRepository repository;
    private LoginService loginService;

    @Autowired
    public CategoriaService(CategoriaRepository repository, LoginService loginService) {
        this.repository = repository;
        this.loginService = loginService;
    }

   public Categoria buscarPeloId(long idCategoria) throws CategoriaNaoExisteException {
        if(!repository.existsById(idCategoria))
            throw new CategoriaNaoExisteException(idCategoria);

        return repository.findById(idCategoria).get();
    }

    public void cadastrar(HttpHeaders headers, CategoriaRequest categoriaRequest) throws AmbiguidadeDeNomesCategoriasException, UsuarioNaoEAdministradorException, UsuarioNaoLogadoException {
        loginService.verificaSeTokenValidoESeAdministradorELancaExcecoes(headers);
        if(repository.existsByNome(categoriaRequest.getNome()))
            throw new AmbiguidadeDeNomesCategoriasException(categoriaRequest.getNome());

        Categoria categoria = new Categoria(categoriaRequest);
        		
        repository.save(categoria);
    }
    
    public List<Categoria> listarTodosOrdemAlfabetica(HttpHeaders headers) throws UsuarioNaoLogadoException {
        if(!loginService.verificaSeTokenValido(headers))
            throw new UsuarioNaoLogadoException();

    	return this.repository.findAllByOrderByNomeAsc();
    }
}
