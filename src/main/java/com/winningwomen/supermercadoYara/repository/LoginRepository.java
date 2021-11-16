package com.winningwomen.supermercadoYara.repository;

import com.winningwomen.supermercadoYara.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<Login, Long> {
    Login save(Login login);
    Optional<Login> findById(Long login);
}
