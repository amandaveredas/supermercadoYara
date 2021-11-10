package com.winningwomen.supermercadoYara.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.winningwomen.supermercadoYara.model.Funcao;

@Repository
public interface FuncaoRepository extends JpaRepository<Funcao, Long> {

}
