package com.devsu.client.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AccountMovementDto {

    private Date transactionDate;
    private Long accountNumber;
    private String accountType;
    private BigDecimal initialAmount;
    private BigDecimal transactionAmount;
    private BigDecimal resultantAmount;
    private Boolean status;
}
