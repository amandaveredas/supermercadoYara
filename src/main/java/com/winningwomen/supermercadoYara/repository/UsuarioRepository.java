package com.winningwomen.supermercadoYara.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.winningwomen.supermercadoYara.model.Usuario;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    boolean existsByNome(String user_name);
    List<Usuario> findAll();
}
