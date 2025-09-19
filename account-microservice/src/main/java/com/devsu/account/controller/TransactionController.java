package com.devsu.account.controller;

import com.devsu.account.DTO.TransactionRequestDto;
import com.devsu.account.DTO.TransactionResponseDto;
import com.devsu.account.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@Validated
@RequestMapping("/movimientos")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;
    @GetMapping()
    public ResponseEntity<List<TransactionResponseDto>> getAllTransactions(){
        return new ResponseEntity<>(transactionService.getAllTransaction(), HttpStatus.OK);
    }
    @GetMapping("/cuentas/{accountId}")
    public ResponseEntity<List<TransactionResponseDto>> getAllAccountTransactions(@PathVariable("accountId") Long accountId){
        return new ResponseEntity<>(transactionService.getTransactionByAccountId(accountId), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<TransactionResponseDto> getTransactionById(@PathVariable("id") Long transactionId){
        return new ResponseEntity<>(transactionService.getTransactionById(transactionId), HttpStatus.OK);
    }
    @PostMapping()
    public ResponseEntity<TransactionResponseDto> createTransaction(@RequestBody @Valid TransactionRequestDto transactionRequestDto){
        return new ResponseEntity<>(transactionService.createTransaction(transactionRequestDto), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<TransactionResponseDto> updateTransaction(@PathVariable("id") Long transactionId, @RequestBody TransactionRequestDto transactionRequestDto ){
        return new ResponseEntity<>(transactionService.updateTransaction(transactionId, transactionRequestDto), HttpStatus.OK);
    }

}
