package com.devsu.client.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ClientReportDto {

    @JsonProperty("Fecha")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date transactionDate;
    @JsonProperty("Cliente")
    private String client;
    @JsonProperty("Numero Cuenta")
    private Long accountNumber;
    @JsonProperty("Tipo")
    private String accountType;
    @JsonProperty("Saldo Inicial")
    private BigDecimal initialAmount;
    @JsonProperty("Estado")
    private Boolean status;
    @JsonProperty("Movimiento")
    private BigDecimal transactionAmount;
    @JsonProperty("Saldo Disponible")
    private BigDecimal resultantAmount;
}
