package com.winningwomen.supermercadoYara.service;

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

   public Categoria buscarPeloId(long idCategoria) {
        return repository.findById(idCategoria).get();
    }
}
