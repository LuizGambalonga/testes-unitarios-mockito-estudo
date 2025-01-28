package com.gambalongasys.database.api;

import com.gambalongasys.database.model.DatabaseModel;
import com.gambalongasys.database.service.DatabaseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/database")
public class DatabaseController {

    @Autowired
    private final DatabaseService databaseService;

    public DatabaseController(final DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    @GetMapping("/{idDatabase}")
    private ResponseEntity <DatabaseModel> getDatabasePorId(@PathVariable  Long idDatabase){
        DatabaseModel dtb = databaseService.buscarDatabasePorId(idDatabase);
        if (dtb !=null){
            return ResponseEntity.status(HttpStatus.OK).body(dtb);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping
    public ResponseEntity<DatabaseModel> criarDatabase(@RequestBody DatabaseModel databaseModel){
        DatabaseModel dtb = databaseService.salvarDatabase(databaseModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(dtb);
    }

    @PutMapping("/{idDatabase}")
    private  ResponseEntity<DatabaseModel> atualizarUmDatabase(@PathVariable final Long idDatabase,
                                                               @RequestBody final DatabaseModel databaseModelNovo){

        final DatabaseModel dtbExistente = databaseService.buscarDatabasePorId(idDatabase);

        if (dtbExistente !=null){
            BeanUtils.copyProperties(databaseModelNovo, dtbExistente, "id");
           return ResponseEntity.status(HttpStatus.OK).body(databaseService.salvarDatabase(dtbExistente));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
