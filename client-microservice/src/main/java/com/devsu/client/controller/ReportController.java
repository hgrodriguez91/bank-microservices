package com.devsu.client.controller;

import com.devsu.client.DTO.ClientReportDto;
import com.devsu.client.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@Validated
@RequestMapping("reportes")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService){
        this.reportService = reportService;
    }

    @GetMapping()
    public ResponseEntity<List<ClientReportDto>> getTransactionClientReport(
            @RequestParam(name = "fechaInicial") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate initDate,
            @RequestParam(name = "fechaFinal") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
            @RequestParam(name = "client") Long client) {

        return ResponseEntity.ok(reportService.getTransactions(client, initDate, endDate));
    }
}
