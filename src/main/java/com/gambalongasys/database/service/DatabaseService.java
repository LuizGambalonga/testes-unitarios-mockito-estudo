package com.gambalongasys.database.service;

import com.gambalongasys.database.model.DatabaseModel;
import com.gambalongasys.database.repository.DatabaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DatabaseService {

    @Autowired
    private DatabaseRepository repository;

    public List<DatabaseModel> buscarTodasDatabase(){
        return repository.findAll();
    }

    public DatabaseModel buscarDatabasePorId(final Long idDatabase){
        return repository.findByIdDatabase(idDatabase);
    }

    public DatabaseModel salvarDatabae(final DatabaseModel databaseModel){
       return repository.save(databaseModel);
    }

    public void deletarDatabasePorId(final Long idDatabase){
        final Optional<DatabaseModel> database = Optional.ofNullable(buscarDatabasePorId(idDatabase));
        database.ifPresent(databaseModel -> repository.deleteById(databaseModel.getId()));
    }
}
