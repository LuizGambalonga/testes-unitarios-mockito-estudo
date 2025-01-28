package com.gambalongasys.usuario.api;

import com.gambalongasys.usuario.model.UsuarioModel;
import com.gambalongasys.usuario.service.UsuarioService;
import com.gambalongasys.database.model.DatabaseModel;
import com.gambalongasys.database.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/usuarios")
public class UsuarioController {

    @Autowired
    private final UsuarioService usuarioService;

    @Autowired
    private final DatabaseService databaseService;  // Serviço para buscar o tenant

    public UsuarioController(final UsuarioService usuarioService, final DatabaseService databaseService) {
        this.usuarioService = usuarioService;
        this.databaseService = databaseService;
    }

    @PostMapping
    public ResponseEntity<UsuarioModel> criarUsuario(@RequestBody UsuarioModel usuarioModel,
                                                     @RequestHeader("Tenant-ID") Long tenantId) {

        // Verifica se o Tenant existe
        DatabaseModel databaseModel = databaseService.buscarDatabasePorId(tenantId);

        if (databaseModel == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);  // Retorna 404 caso o tenant não exista
        }

        // Associa o tenant (DatabaseModel) ao novo usuário
        usuarioModel.setDatabase(databaseModel);  // Associação do tenant

        // Salva o usuário
        UsuarioModel usuarioCriado = usuarioService.salvarUsuario(usuarioModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioCriado);
    }
}