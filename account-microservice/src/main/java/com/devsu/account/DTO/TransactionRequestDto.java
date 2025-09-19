package com.devsu.account.DTO;

import jakarta.validation.constraints.NotNull;
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
public class TransactionRequestDto {
    private Date transactionDate;
    @NotNull(message = "The transaction amount is required")
    private BigDecimal transactionAmount;
    @NotNull(message = "The field accountId is required")
    private Long accountId;
}
