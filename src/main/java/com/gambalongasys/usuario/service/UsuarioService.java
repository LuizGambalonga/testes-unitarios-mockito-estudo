package com.gambalongasys.usuario.service;

import com.gambalongasys.usuario.model.UsuarioModel;
import com.gambalongasys.usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioModel salvarUsuario(UsuarioModel usuarioModel) {
        return usuarioRepository.save(usuarioModel);
    }
}