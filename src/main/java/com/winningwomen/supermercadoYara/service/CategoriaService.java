package com.winningwomen.supermercadoYara.service;

import com.winningwomen.supermercadoYara.dto.request.CategoriaRequest;
import com.winningwomen.supermercadoYara.exception.AmbiguidadeDeNomesCategoriasException;
import com.winningwomen.supermercadoYara.exception.CategoriaNaoExisteException;
import com.winningwomen.supermercadoYara.model.Categoria;
import com.winningwomen.supermercadoYara.repository.CategoriaRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {
    private CategoriaRepository repository;

    @Autowired
    public CategoriaService(CategoriaRepository repository) {
        this.repository = repository;
    }

   public Categoria buscarPeloId(long idCategoria) throws CategoriaNaoExisteException {
        if(!repository.existsById(idCategoria))
            throw new CategoriaNaoExisteException(idCategoria);

        return repository.findById(idCategoria).get();
    }

    public void cadastrar(CategoriaRequest categoriaRequest) throws AmbiguidadeDeNomesCategoriasException {
        if(repository.existsByNome(categoriaRequest.getNome()))
            throw new AmbiguidadeDeNomesCategoriasException(categoriaRequest.getNome());

        Categoria categoria = new Categoria(categoriaRequest);
        		
        repository.save(categoria);
    }
    
    public List<Categoria> listarTodosOrdemAlfabetica(){
    	return this.repository.findAllByOrderByNomeAsc();
    }
}
