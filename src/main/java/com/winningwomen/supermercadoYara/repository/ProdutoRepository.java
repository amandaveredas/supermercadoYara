package com.winningwomen.supermercadoYara.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.winningwomen.supermercadoYara.model.Produto;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{

    boolean existsByNome(String nome);
    List<Produto> findAll();
    List<Produto> findAllByOrderByNomeAsc();
}
