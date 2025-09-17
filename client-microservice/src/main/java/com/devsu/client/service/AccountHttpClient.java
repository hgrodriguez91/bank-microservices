package com.devsu.client.service;

import com.devsu.client.DTO.AccountMovementDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@FeignClient(name = "accountHttpClient", url = "${app.remote.account-service}")
public interface AccountHttpClient {

    @GetMapping("cuentas/reportes/{clientId}")
    List<AccountMovementDto> getTransactionByUser(  @PathVariable("clientId") Long clientId,
                                                    @RequestParam(name = "fechaInicial") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate initDate,
                                                    @RequestParam(name = "fechaFinal") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate);
}
