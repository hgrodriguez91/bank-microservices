package com.devsu.account.DTO;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AccountTransactionDto {
    private Date transactionDate;
    private String accountNumber;
    private String accountType;
    private BigDecimal initialAmount;
    private BigDecimal transactionAmount;
    private BigDecimal resultantAmount;
    private Boolean status;
}
