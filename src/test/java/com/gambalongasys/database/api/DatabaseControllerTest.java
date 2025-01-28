package com.gambalongasys.database.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gambalongasys.database.DatabaseDadosTest;
import com.gambalongasys.database.model.DatabaseModel;
import com.gambalongasys.database.service.DatabaseService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DatabaseController.class)
@AutoConfigureMockMvc
class DatabaseControllerTest {

    private final static String pathApi = "/database";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DatabaseService databaseService;

    @BeforeEach
    void setUp() {
    }

    @Test
    public void deveRecuperarDatabasePorIdERetornaSucesso() throws Exception {
        Long idDatabase = 1L;
        DatabaseModel databaseModel = new DatabaseModel();
        databaseModel.setId(idDatabase);
        databaseModel.setNome("Teste");

        when(databaseService.buscarDatabasePorId(idDatabase)).thenReturn(databaseModel);

        mockMvc.perform(MockMvcRequestBuilders.get(pathApi + "/{id}", idDatabase))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(idDatabase))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Teste"));
    }

    @Test
    public void deveRecuperarUmaDatabasePorIdERetornaNotFound() throws Exception {

        when(databaseService.buscarDatabasePorId(anyLong())).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.get(pathApi + "/{id}", 1L))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deveSalvarUmDatabase() throws Exception {
        when(databaseService.salvarDatabase(any(DatabaseModel.class))).thenReturn(DatabaseDadosTest.CriarDatabaseComCpfECnpjVazio());

        ObjectMapper objtMapper = new ObjectMapper();
        String jsonRequest = objtMapper.writeValueAsString(DatabaseDadosTest.CriarDatabaseComCpfECnpjVazio());

        mockMvc.perform(MockMvcRequestBuilders.post(pathApi)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                    .andExpect(status().isCreated())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());

        verify(databaseService, times(1)).salvarDatabase(any(DatabaseModel.class));
    }

    @Test
    public void deveAtualizarUmDatabaseExistente() throws Exception {
        Long idDatabase = 1L;
        DatabaseModel databaseNovo = DatabaseDadosTest.CriarDatabase();
        DatabaseModel databaseExistente = DatabaseDadosTest.CriarDatabaseComCpfECnpjVazio();
        databaseExistente.setId(idDatabase);

        ObjectMapper objectMapper = new ObjectMapper();

        when(databaseService.buscarDatabasePorId(idDatabase)).thenReturn(databaseExistente);
        when(databaseService.salvarDatabase(any(DatabaseModel.class))).thenReturn(databaseExistente);

        ResultActions retornoApi = mockMvc.perform(put(pathApi + "/{id}", idDatabase)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(databaseNovo)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(idDatabase))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value(databaseNovo.getNome()));

        DatabaseModel dtb = objectMapper.readValue(retornoApi.andReturn().getResponse().getContentAsString(),
                DatabaseModel.class);

        Assertions.assertEquals(databaseNovo.getId() , dtb.getId());
        verify(databaseService, times(1)).buscarDatabasePorId(idDatabase);
        verify(databaseService, times(1)).salvarDatabase(any(DatabaseModel.class));
    }

    @Test
    public void deveRetornarNotFoundAoAtualizarDatabaseInexistente() throws Exception {
        Long idDatabase = 1L;
        DatabaseModel databaseModelNovo = DatabaseDadosTest.CriarDatabaseComCpfECnpjVazio();

        ObjectMapper objectMapper = new ObjectMapper();

        when(databaseService.buscarDatabasePorId(idDatabase)).thenReturn(null);

        mockMvc.perform(put(pathApi + "{id}", idDatabase)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(databaseModelNovo)))
                .andExpect(status().isNotFound());

        verify(databaseService, never()).salvarDatabase(any(DatabaseModel.class));
    }
}