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


    private final UsuarioService usuarioService;
    private final DatabaseService databaseService;

    public UsuarioController(final UsuarioService usuarioService, final DatabaseService databaseService) {
        this.usuarioService = usuarioService;
        this.databaseService = databaseService;
    }

    @PostMapping
    public ResponseEntity<UsuarioModel> criarUsuario(@RequestBody UsuarioModel usuarioModel,
                                                     @RequestHeader("Tenant-ID") Long tenantId) {


        DatabaseModel databaseModel = databaseService.buscarDatabasePorId(tenantId);

        if (databaseModel == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }


        usuarioModel.setDatabase(databaseModel);

        UsuarioModel usuarioCriado = usuarioService.salvarUsuario(usuarioModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioCriado);
    }
}