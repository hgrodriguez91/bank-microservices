package com.devsu.account.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TransactionResponseDto {
    private Long id;
    private Date transactionDate;
    private String transactionType;
    private BigDecimal transactionAmount;
    private BigDecimal resultAmount;
    private Long accountId;
    private String accountNumber;
}
