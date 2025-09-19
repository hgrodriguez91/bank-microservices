package com.devsu.account.service;

import com.devsu.account.DTO.TransactionRequestDto;
import com.devsu.account.DTO.TransactionResponseDto;

import java.util.List;

public interface TransactionService {

    List<TransactionResponseDto> getAllTransaction();
    List<TransactionResponseDto> getTransactionByAccountId(Long accountId);
    TransactionResponseDto getTransactionById(Long transactionId);
    TransactionResponseDto createTransaction(TransactionRequestDto transactionRequestDto);
    TransactionResponseDto updateTransaction(Long transactionId, TransactionRequestDto transactionRequestDto);

}
