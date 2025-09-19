package com.devsu.account.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AccountDto {

    private Long id;
    @NotNull(message = "The field accountNumber is required")
    private String accountNumber;
    @NotNull(message = "The field accountType is required")
    private String accountType;
    @Builder.Default
    private BigDecimal initialAmount = BigDecimal.ZERO ;
    @Builder.Default
    private Boolean status = Boolean.TRUE;
    @NotNull(message = "The field client id is required")
    private Long clientId;
}
