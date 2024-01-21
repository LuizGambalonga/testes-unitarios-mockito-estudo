package com.gambalongasys.database.service;

import com.gambalongasys.database.DatabaseDadosTest;
import com.gambalongasys.database.model.DatabaseModel;
import com.gambalongasys.database.repository.DatabaseRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;


class DatabaseServiceTest {

    @InjectMocks
    private DatabaseService databaseService;
    @Mock
    private DatabaseRepository repositoryMock;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveBuscarTodasDatabases() {
        when(repositoryMock.findAll()).thenReturn(DatabaseDadosTest.listaDeDatabases());

        List<DatabaseModel> result = databaseService.buscarTodasDatabase();

        Assertions.assertArrayEquals(DatabaseDadosTest.listaDeDatabases().toArray(), result.toArray());
    }

    @Test
    void deveBuscarUmaDatabasePorId() {
        when(repositoryMock.findByIdDatabase(anyLong())).thenReturn(DatabaseDadosTest.CriarDatabase());
        DatabaseModel result = databaseService.buscarDatabasePorId(1L);

        verify(repositoryMock).findByIdDatabase(eq(1L));

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("PAMELA", result.getNome());
    }

    @Test
    void deveSalvarUmaDatabase() {
        DatabaseModel dtbparaSalvar = DatabaseDadosTest.CriarDatabase();

        when(repositoryMock.save(any(DatabaseModel.class))).thenReturn(dtbparaSalvar);

        DatabaseModel result = databaseService.salvarDatabase(dtbparaSalvar);

        verify(repositoryMock).save(eq(dtbparaSalvar));
        assertNotNull(result);
    }

    @Test
    void deveDeletarUmaDatabasePorId() {
        Long databaseIdDelete = 1L;

        DatabaseModel databaseparaDeletar = DatabaseDadosTest.CriarDatabase();

        when(repositoryMock.findByIdDatabase(eq(databaseIdDelete))).thenReturn((databaseparaDeletar));

        databaseService.deletarDatabasePorId(databaseIdDelete);

        verify(repositoryMock).findByIdDatabase(eq(databaseIdDelete));
        verify(repositoryMock).deleteById(eq(databaseparaDeletar.getId()));

        verify(repositoryMock, times(1)).findByIdDatabase(anyLong());
        verify(repositoryMock, times(1)).deleteById(anyLong());

        InOrder inOrder = inOrder(repositoryMock);
        inOrder.verify(repositoryMock).findByIdDatabase(eq(databaseIdDelete));
        inOrder.verify(repositoryMock).deleteById(eq(databaseparaDeletar.getId()));
    }
}