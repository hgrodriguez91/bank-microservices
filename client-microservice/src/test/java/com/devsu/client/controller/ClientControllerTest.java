package com.devsu.client.controller;

import com.devsu.client.DTO.ClientRequestDto;
import com.devsu.client.entity.Client;
import com.devsu.client.repository.ClientRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Client existingClient;

    @BeforeEach
    void setup() {
        existingClient = new Client();
        existingClient.setName("Juan Pérez");
        existingClient.setGender("M");
        existingClient.setAge(30);
        existingClient.setIdentification("123456789");
        existingClient.setAddress("Calle 123");
        existingClient.setPhone("5551234567");
        existingClient.setPassword("secret");
        existingClient.setStatus(true);

        clientRepository.save(existingClient);
    }


    @Test
    void testGetClientById() throws Exception {
        mockMvc.perform(get("/clientes/{id}", existingClient.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Juan Pérez")))
                .andExpect(jsonPath("$.gender", is("M")))
                .andExpect(jsonPath("$.identification", is("123456789")))
                .andExpect(jsonPath("$.clientId", notNullValue()));
    }

    @Test
    void testCreateClient() throws Exception {
        ClientRequestDto request = new ClientRequestDto();
        request.setName("Maria López");
        request.setGender("F");
        request.setAge(25);
        request.setIdentification("987654321");
        request.setAddress("Calle 456");
        request.setPhone("5559876543");
        request.setPassword("pass123");

        mockMvc.perform(post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("Maria López")))
                .andExpect(jsonPath("$.clientId", notNullValue()));

        assert (clientRepository.findAll().size() == 2);
    }

    @Test
    void testUpdateClient() throws Exception {
        ClientRequestDto updateRequest = new ClientRequestDto();
        updateRequest.setName("Juan Actualizado");
        updateRequest.setGender("M");
        updateRequest.setAge(31);
        updateRequest.setIdentification("123456789");
        updateRequest.setAddress("Calle 999");
        updateRequest.setPhone("5550000000");
        updateRequest.setPassword("newpass");

        mockMvc.perform(put("/clientes/{id}", existingClient.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Juan Actualizado")))
                .andExpect(jsonPath("$.clientId", notNullValue()));
    }

    @Test
    void testDisableClient() throws Exception {
        mockMvc.perform(patch("/clientes/{id}", existingClient.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is("Cliente deshabilitado")));

        assert (!clientRepository.findById(existingClient.getId()).get().getStatus());
    }

    @Test
    void testDeleteClient() throws Exception {
        mockMvc.perform(delete("/clientes/{id}", existingClient.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is("Cliente eliminado")));

        assert (clientRepository.findById(existingClient.getId()).isEmpty());
    }
}
