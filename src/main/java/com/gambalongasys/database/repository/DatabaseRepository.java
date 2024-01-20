package com.gambalongasys.database.repository;

import com.gambalongasys.database.model.DatabaseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DatabaseRepository extends JpaRepository  <DatabaseModel, Long>{
    List<DatabaseModel> findByNome(final String nome);
    @Query("SELECT d FROM DatabaseModel d WHERE d.id = :id")
    DatabaseModel findByIdDatabase(@Param("id") Long id);
}
