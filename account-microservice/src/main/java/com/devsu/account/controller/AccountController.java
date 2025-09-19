package com.devsu.account.controller;

import com.devsu.account.DTO.AccountDto;
import com.devsu.account.DTO.AccountTransactionDto;
import com.devsu.account.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@Validated
@RequestMapping("/cuentas")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping()
    @Operation(summary = "Obtener todas las cuentas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de cuentas obtenida correctamente")
    })
    public ResponseEntity<List<AccountDto>> getAllAccounts() {
        return new ResponseEntity<>(accountService.getAllAccounts(), HttpStatus.OK);
    }

    @GetMapping("/cliente/{clientId}")
    public ResponseEntity<List<AccountDto>> getAllClientAccounts(@PathVariable("clientId") Long clientId) {
        return new ResponseEntity<>(accountService.getAccountsForClient(clientId), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable("id") Long accountId) {
        return new ResponseEntity<>(accountService.getAccountById(accountId), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<AccountDto> createAccount(@RequestBody @Valid AccountDto accountDto) {
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountDto> updateAccount(@PathVariable("id") Long accountId, @RequestBody AccountDto accountDto) {
        return new ResponseEntity<>(accountService.updateAccount(accountId, accountDto), HttpStatus.OK);
    }

    @GetMapping("reportes/{clientId}")
    public ResponseEntity<List<AccountTransactionDto>> getAccountTransactions(@PathVariable("clientId") Long clientId,
                                                                              @RequestParam(name = "fechaInicial") @DateTimeFormat(pattern = "yyyy-MM-dd") Date initDate,
                                                                              @RequestParam(name = "fechaFinal") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        return new ResponseEntity<>(accountService.getHistoryForClient(clientId, initDate, endDate), HttpStatus.OK);
    }
}
