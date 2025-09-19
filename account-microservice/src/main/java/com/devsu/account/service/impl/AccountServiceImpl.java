package com.devsu.account.service.impl;

import com.devsu.account.DTO.AccountDto;
import com.devsu.account.DTO.AccountTransactionDto;
import com.devsu.account.entity.Account;
import com.devsu.account.entity.AccountTransactionProjection;
import com.devsu.account.exception.NotFoundException;
import com.devsu.account.mapper.AccountMapper;
import com.devsu.account.repository.AccountRepository;
import com.devsu.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {

    private final AccountRepository  accountRepository;
    private final AccountMapper accountMapper;
    @Override
    public List<AccountDto> getAllAccounts() {
        log.info("Getting all accounts");
        return accountMapper.toAccountDtoList(accountRepository.findAll());
    }

    @Override
    public List<AccountDto> getAccountsForClient(Long clientId) {
        log.info("Getting account for client : {}", clientId);
        List<Account> accounts = accountRepository.findAllByClientId(clientId);
        return accountMapper.toAccountDtoList(accounts);
    }

    @Override
    public AccountDto getAccountById(Long accountId) {
        log.info("Getting account with id: {}", accountId);
        Account account = accountRepository.findById(accountId).orElseThrow(()-> new NotFoundException("Cuenta no encontrada"));
        return accountMapper.toAccountDto(account);
    }

    @Override
    public List<AccountTransactionDto> getHistoryForClient(Long clientId, Date initDate, Date endDate) {
        log.info("Getting transaction history for client: {}", clientId);
        List<AccountTransactionProjection> accountTransactionHistory = accountRepository.getAccountTransactionHistory(clientId, initDate, endDate);
        return accountMapper.toAccountTransactionDto(accountTransactionHistory);
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        log.info("Creating account for user: {}", accountDto.getClientId());
        log.info("With account data: {}", accountDto);
        Account account =   accountMapper.toAccount(accountDto);
        accountRepository.save(account);
        return accountMapper.toAccountDto(account);
    }

    @Override
    public AccountDto updateAccount(Long accountId, AccountDto accountDto) {
        log.info("Update account with id: {}", accountId);
        Account account = accountRepository.findById(accountId).orElseThrow(()-> new NotFoundException("No se encontró la cuenta especificada"));
        accountMapper.updateActionFromDto(accountDto,account);
        accountRepository.save(account);
        return accountMapper.toAccountDto(account);
    }
}
