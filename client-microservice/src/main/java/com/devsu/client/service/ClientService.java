package com.devsu.client.service;

import com.devsu.client.DTO.ClientRequestDto;
import com.devsu.client.DTO.ClientResponseDto;
import com.devsu.client.DTO.GenericMessageResponseDto;
import com.devsu.client.entity.Client;

import java.util.List;

public interface ClientService {

    List<ClientResponseDto> getAllClients();
    ClientResponseDto getClient(Long clientId);
    ClientResponseDto createClient(ClientRequestDto client);
    ClientResponseDto updateClient(Long clientId, ClientRequestDto clientUpdateData);

    GenericMessageResponseDto disableClient( Long clientId);
    GenericMessageResponseDto deleteClient( Long clientId);
}
