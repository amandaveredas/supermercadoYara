package com.winningwomen.supermercadoYara.service;

import com.winningwomen.supermercadoYara.exception.AmbiguidadeDeNomesCategoriasException;
import com.winningwomen.supermercadoYara.exception.AmbiguidadeDeNomesProdutosException;
import com.winningwomen.supermercadoYara.exception.CategoriaNaoExisteException;
import com.winningwomen.supermercadoYara.model.Categoria;
import com.winningwomen.supermercadoYara.repository.CategoriaRepository;
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

    public void cadastrar(Categoria categoria) throws AmbiguidadeDeNomesCategoriasException {
        if(repository.existsByNome(categoria.getNome()))
            throw new AmbiguidadeDeNomesCategoriasException(categoria.getNome());

        repository.save(categoria);
    }
}
