package com.devsu.client.service.impl;

import com.devsu.client.DTO.ClientResponseDto;
import com.devsu.client.entity.Client;
import com.devsu.client.mapper.ClientMapper;
import com.devsu.client.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@DisplayName("ClientService testing")
class ClientServiceImplTest {
    @Mock
    private ClientMapper clientMapper;
    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientServiceImpl clientService;

    private final Client client = new Client();
    private final ClientResponseDto clienteResponseDto = new ClientResponseDto();

    private final String clientId = java.util.UUID.randomUUID().toString();

    @BeforeEach
    void setUp() {
        client.setId(1L);
        client.setClientId(clientId);
        client.setName("Jhon Doe");
        client.setAge(32);
        client.setPhone("123456789");
        client.setGender("M");
        client.setAddress("Boulevar Street");
        client.setPassword("password");
        client.setStatus(true);
        client.setIdentification("92112345982");

        clienteResponseDto.setClientId(clientId);
        clienteResponseDto.setId(1L);
        clienteResponseDto.setName("Jhon Doe");
        clienteResponseDto.setAge(32);
        clienteResponseDto.setPhone("123456789");
        clienteResponseDto.setGender("M");
        clienteResponseDto.setAddress("Boulevar Street");
        clienteResponseDto.setStatus(true);
        clienteResponseDto.setIdentification("92112345982");

    }

    @Test
    @DisplayName("Testing getAllClients method")
    void getAllClients() {
        given(clientRepository.findAll()).willReturn(List.of(client));
        given(clientMapper.toClientDtoList(clientRepository.findAll())).willReturn(List.of(clienteResponseDto));
        List<ClientResponseDto> clientList = clientService.getAllClients();
        assertNotNull(clientList);
        assertEquals(1, clientList.size());
        assertEquals("Jhon Doe", clientList.get(0).getName());
        assertEquals(1L, clientList.get(0).getId());
    }

    @Test
    void getClient() {
        given(clientRepository.findById(anyLong())).willReturn(Optional.of(client));
        given(clientMapper.toClientDto(any(Client.class))).willReturn(clienteResponseDto);
        ClientResponseDto clientResponseDto = clientService.getClient(anyLong());
        assertEquals(clientId, clientResponseDto.getClientId());
        assertEquals("Jhon Doe", clientResponseDto.getName());
    }

}
