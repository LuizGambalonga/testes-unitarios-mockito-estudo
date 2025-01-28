package com.gambalongasys.usuario.repository;

import com.gambalongasys.usuario.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {
}