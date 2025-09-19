package com.devsu.account.mapper;

import com.devsu.account.DTO.AccountDto;
import com.devsu.account.DTO.AccountTransactionDto;
import com.devsu.account.entity.Account;
import com.devsu.account.entity.AccountTransactionProjection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AccountMapper {

    AccountDto toAccountDto(Account account);

    Account toAccount(AccountDto accountDto);

    List<AccountDto> toAccountDtoList(List<Account> accounts);

    void updateActionFromDto(AccountDto dto, @MappingTarget Account entity);
    @Mapping(target ="transactionDate" , source = "transactionDate")
    @Mapping(target ="accountNumber" , source = "accountNumber")
    @Mapping(target ="accountType", source = "accountType")
    @Mapping(target ="initialAmount", source = "initialAmount")
    @Mapping(target ="transactionAmount", source = "transactionAmount")
    @Mapping(target ="resultantAmount", source = "resultantAmount")
    @Mapping(target ="status", source = "status")
    AccountTransactionDto toAccountTransactionDto(AccountTransactionProjection accountTransactions);

    List<AccountTransactionDto> toAccountTransactionDto(List<AccountTransactionProjection> accountTransactions);
}

