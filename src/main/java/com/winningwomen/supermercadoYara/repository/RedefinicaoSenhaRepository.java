package com.winningwomen.supermercadoYara.repository;

import com.winningwomen.supermercadoYara.model.RedefinicaoSenha;
import com.winningwomen.supermercadoYara.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RedefinicaoSenhaRepository extends JpaRepository<RedefinicaoSenha,Long> {

    RedefinicaoSenha save(RedefinicaoSenha recuperacaoSenha);
    void deleteAllByUsuarioEquals(Usuario usuario);

}
