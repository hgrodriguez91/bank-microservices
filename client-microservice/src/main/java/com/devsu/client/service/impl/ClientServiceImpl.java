package com.devsu.client.service.impl;

import com.devsu.client.DTO.ClientRequestDto;
import com.devsu.client.DTO.ClientResponseDto;
import com.devsu.client.DTO.GenericMessageResponseDto;
import com.devsu.client.entity.Client;
import com.devsu.client.exception.NotFoundException;
import com.devsu.client.mapper.ClientMapper;
import com.devsu.client.repository.ClientRepository;
import com.devsu.client.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClientServiceImpl implements ClientService {
    private static final String NOT_FOUND_MESSAGE = "Cliente no encontrado";

    private final ClientMapper clientMapper;
    private final ClientRepository clientRepository;

    @Override
    public List<ClientResponseDto> getAllClients() {
        log.info("Get clients list");
        return clientMapper.toClientDtoList(clientRepository.findAll());
    }

    @Override
    public ClientResponseDto getClient(Long clientId) {
        log.info("Get client data for clientId: {}", clientId);
        Client client = clientRepository.findById(clientId).
                orElseThrow(() -> new NotFoundException(NOT_FOUND_MESSAGE));
        return clientMapper.toClientDto(client);
    }

    @Override
    public ClientResponseDto createClient(ClientRequestDto client) {
        log.info("Create cliente with data: {}", client);
        Client dbClient = new Client();
        clientMapper.updateClientFromDto(client, dbClient);
        dbClient.setStatus(Boolean.TRUE);
        clientRepository.save(dbClient);
        return clientMapper.toClientDto(dbClient);
    }

    @Override
    public ClientResponseDto updateClient(Long clientId, ClientRequestDto clientUpdateData) {
        log.info("Update client with id: {}", clientId);
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new NotFoundException(NOT_FOUND_MESSAGE));
        clientMapper.updateClientFromDto(clientUpdateData, client);
        clientRepository.save(client);
        return clientMapper.toClientDto(client);
    }

    @Override
    public GenericMessageResponseDto disableClient(Long clientId) {
        log.info("Update client with id: {}", clientId);
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new NotFoundException(NOT_FOUND_MESSAGE));
        client.setStatus(Boolean.FALSE);
        clientRepository.save(client);
        return new GenericMessageResponseDto("Cliente deshabilitado");
    }

    @Override
    public GenericMessageResponseDto deleteClient(Long clientId) {
        log.info("Delete client with id: {}", clientId);
        Client client = clientRepository.findById(clientId).
                orElseThrow(() -> new NotFoundException(NOT_FOUND_MESSAGE));
        //Soft delete for history
        //client.setStatus(Boolean.FALSE);
        //Hard delete client
        clientRepository.delete(client);
        return new GenericMessageResponseDto("Cliente eliminado");
    }


}
