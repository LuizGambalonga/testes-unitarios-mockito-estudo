package com.gambalongasys.database.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseModelTest {

    @Test
    public void deveCriarUmaInstanciaDeDatabase() {
        DatabaseModel databaseModel = new DatabaseModel();
        databaseModel.setId(1L);
        databaseModel.setNome("LUIZ");
        databaseModel.setCpf("099.520.555-44");
        databaseModel.setCnpj("123456789012345678");

        assertEquals(1L, databaseModel.getId());
        assertEquals("LUIZ", databaseModel.getNome());
        assertEquals("099.520.555-44", databaseModel.getCpf());
        assertEquals("123456789012345678", databaseModel.getCnpj());
    }
}