package com.devsu.client.service;

import com.devsu.client.DTO.ClientReportDto;

import java.time.LocalDate;
import java.util.List;

public interface ReportService {

    List<ClientReportDto> getTransactions(Long clientId, LocalDate initDate, LocalDate endDate);
}
