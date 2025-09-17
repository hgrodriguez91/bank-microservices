package com.devsu.client.controller;

import com.devsu.client.DTO.ClientRequestDto;
import com.devsu.client.DTO.ClientResponseDto;
import com.devsu.client.DTO.GenericMessageResponseDto;
import com.devsu.client.service.ClientService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@Validated
@RequestMapping("/clientes")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService){
        this.clientService = clientService;
    }

    @GetMapping()
    public ResponseEntity<List<ClientResponseDto>> getAllClients() {
        return new ResponseEntity<>(this.clientService.getAllClients(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientResponseDto> getClientById(@PathVariable("id") Long clientId) {
        return new ResponseEntity<>(this.clientService.getClient(clientId), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<ClientResponseDto> createClient(@RequestBody @Valid ClientRequestDto client){
        return new ResponseEntity<>(this.clientService.createClient(client),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientResponseDto> updateClient(@PathVariable("id") Long clientId, @RequestBody ClientRequestDto client){
        return new ResponseEntity<>(this.clientService.updateClient(clientId, client), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<GenericMessageResponseDto> changeStatus(@PathVariable("id") Long clientId){
        return new ResponseEntity<>(clientService.disableClient(clientId), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericMessageResponseDto> deleteClient(@PathVariable("id") Long clientId){
        return new ResponseEntity<>(clientService.deleteClient(clientId), HttpStatus.OK);
    }

}
