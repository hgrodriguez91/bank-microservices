package com.devsu.account.service;

import com.devsu.account.DTO.AccountDto;
import com.devsu.account.DTO.AccountTransactionDto;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface AccountService {

    List<AccountDto> getAllAccounts();
    List<AccountDto> getAccountsForClient(Long clientId);
    AccountDto getAccountById(Long accountId);
    List<AccountTransactionDto> getHistoryForClient(Long clientId, Date initDate, Date endDate);
    AccountDto createAccount( AccountDto accountDto);
    AccountDto updateAccount(Long accountId, AccountDto accountDto);


}
