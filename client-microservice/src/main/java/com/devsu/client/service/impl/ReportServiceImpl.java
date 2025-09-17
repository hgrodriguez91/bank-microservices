package com.devsu.client.service.impl;

import com.devsu.client.DTO.AccountMovementDto;
import com.devsu.client.DTO.ClientReportDto;
import com.devsu.client.entity.Client;
import com.devsu.client.exception.NotFoundException;
import com.devsu.client.repository.ClientRepository;
import com.devsu.client.service.AccountHttpClient;
import com.devsu.client.service.ReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReportServiceImpl implements ReportService {
    private static final String NOT_FOUND_MESSAGE = "Cliente no encontrado";
    private final AccountHttpClient accountHttpClient;
    private final ClientRepository clientRepository;
    @Override
    public List<ClientReportDto> getTransactions(Long clientId, LocalDate initDate, LocalDate endDate) {
        Client  client = clientRepository.findById(clientId).orElseThrow(()-> new NotFoundException(NOT_FOUND_MESSAGE));
        List<AccountMovementDto> accountMovements = accountHttpClient.getTransactionByUser(client.getId(), initDate, endDate);
        return accountMovements.stream().map( transaction -> ClientReportDto.builder()
                .accountNumber(transaction.getAccountNumber())
                .accountType(transaction.getAccountType())
                .transactionAmount(transaction.getTransactionAmount())
                .transactionDate(transaction.getTransactionDate())
                .initialAmount(transaction.getInitialAmount())
                .client(client.getName())
                .resultantAmount(transaction.getResultantAmount())
                .status(transaction.getStatus())
                .build()).toList();
    }
}
