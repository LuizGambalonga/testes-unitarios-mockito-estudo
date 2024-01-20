package com.gambalongasys.database.api;

import com.gambalongasys.database.model.DatabaseModel;
import com.gambalongasys.database.service.DatabaseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.anyLong;

@WebMvcTest(DatabaseController.class)
@AutoConfigureMockMvc
class DatabaseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DatabaseService databaseService;

    @BeforeEach
    void setUp() {
    }

    @Test
    public void testGetDatabasePorIdERetornaSucesso() throws Exception {
        Long idDatabase = 1L;
        DatabaseModel databaseModel = new DatabaseModel();
        databaseModel.setId(idDatabase);
        databaseModel.setNome("Teste");

        Mockito.when(databaseService.buscarDatabasePorId(idDatabase)).thenReturn(databaseModel);

        mockMvc.perform(MockMvcRequestBuilders.get("/database/{id}", idDatabase))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(idDatabase))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Teste"));
    }

    @Test
    public void testGetDatabasePorIdERetornaNotFound() throws Exception {

        Mockito.when(databaseService.buscarDatabasePorId(anyLong())).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.get("/database/{id}", 1L))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}