package com.devsu.account.mapper;

import com.devsu.account.DTO.TransactionRequestDto;
import com.devsu.account.DTO.TransactionResponseDto;
import com.devsu.account.entity.Transaction;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,uses = {Transaction.class})
public interface TransactionMapper {

    @Mapping(target ="accountId" , source = "account.id")
    @Mapping(target ="accountNumber" , source = "account.accountNumber")
    TransactionResponseDto toTransactionResponseDto(Transaction transaction);

    List<TransactionResponseDto> toTransactionResponseDtoList(List<Transaction> transaction);
    void updateTransactionFromDto(TransactionRequestDto dto, @MappingTarget Transaction entity);

}
