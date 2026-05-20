package com.revisaospring.springbasic.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revisaospring.springbasic.Entity.Usuario;

@Repository
public interface  UsuarioRepository extends JpaRepository<Usuario, Long>{
    Optional<Usuario>findByUsername(String username);
}
