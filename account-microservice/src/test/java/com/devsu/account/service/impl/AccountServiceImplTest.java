package com.devsu.account.service.impl;

import com.devsu.account.DTO.AccountDto;
import com.devsu.account.entity.Account;
import com.devsu.account.mapper.AccountMapper;
import com.devsu.account.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@DisplayName("AccountService testing")
class AccountServiceImplTest {
    @Mock
    private AccountMapper accountMapper;
    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountServiceImpl accountService;

    private final Account account = new Account();
    private final AccountDto accountDto = new AccountDto();

    private final String accountNumber = java.util.UUID.randomUUID().toString();

    @BeforeEach
    void setUp() {
        account.setAccountNumber(accountNumber);
        account.setAccountType("Ahorros");
        account.setClientId(1L);
        account.setInitialAmount(BigDecimal.valueOf(500));
        account.setId(1L);

        accountDto.setAccountType("Ahorros");
        accountDto.setId(1L);
        accountDto.setStatus(Boolean.TRUE);
        accountDto.setAccountNumber(accountNumber);
        accountDto.setInitialAmount(BigDecimal.valueOf(500));
        accountDto.setClientId(1L);
    }
    @Test
    void getAllAccounts() {
        given(accountRepository.findAll()).willReturn(List.of(account));
        given(accountMapper.toAccountDtoList(accountRepository.findAll())).willReturn(List.of(accountDto));
        List<AccountDto> accountList = accountService.getAllAccounts();
        assertNotNull(accountList);
        assertEquals(1, accountList.size());
        assertEquals(1L, accountList.get(0).getId());
    }

    @Test
    void getAccountsForClient() {
        given(accountRepository.findAllByClientId(any())).willReturn(List.of(account));
        given(accountMapper.toAccountDtoList(accountRepository.findAllByClientId(any()))).willReturn(List.of(accountDto));
        List<AccountDto> accountList = accountService.getAccountsForClient(any());
        assertNotNull(accountList);
        assertEquals(1, accountList.size());
    }

    @Test
    void getAccountById() {
        given(accountRepository.findById(any())).willReturn(Optional.of(account));
        given(accountMapper.toAccountDto(any(Account.class))).willReturn(accountDto);
        AccountDto accountDto1 = accountService.getAccountById(anyLong());
        assertEquals(accountNumber, accountDto1.getAccountNumber());
        assertEquals(1L, accountDto1.getId());
    }

    @Test
    void createAccount() {
        given(accountRepository.save(any(Account.class))).willReturn(account);
        given(accountMapper.toAccount(any(AccountDto.class))).willReturn(account);
        given(accountMapper.toAccountDto(any(Account.class))).willReturn(accountDto);
        AccountDto accountDto1 = accountService.createAccount(accountDto);
        assertEquals(accountNumber, accountDto1.getAccountNumber());
        assertEquals(1L, accountDto1.getId());
    }

}
