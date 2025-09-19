package com.devsu.account.service.impl;

import com.devsu.account.DTO.TransactionRequestDto;
import com.devsu.account.DTO.TransactionResponseDto;
import com.devsu.account.entity.Account;
import com.devsu.account.entity.Transaction;
import com.devsu.account.exception.NotFoundException;
import com.devsu.account.exception.BadRequestCustomException;
import com.devsu.account.mapper.TransactionMapper;
import com.devsu.account.repository.AccountRepository;
import com.devsu.account.repository.TransactionRepository;
import com.devsu.account.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final AccountRepository accountRepository;

    private static final String NOT_FOUND_TRANSACTION_MESSAGE = "Transacción no encontrada";
    private static final String AMOUNT_EXCEDED_MESSAGE = "Saldo no disponible";
    private static final String INVALID_ACCOUNT_MESSAGE = "No hay transacciones para esta cuenta";
    private static final String ONLY_LAST_TRANSACTION_MESSAGE = "Solo es modificable el ultimo movimiento en la cuenta";

    private static final String CREDIT_CARD_TYPE = "Credit";
    private static final String DEBIT_CARD_TYPE = "Debit";

    @Override
    public List<TransactionResponseDto> getAllTransaction() {
        log.info("Getting all transactions");
        List<Transaction> transactions = transactionRepository.findAll();
        return transactionMapper.toTransactionResponseDtoList(transactions);
    }

    @Override
    public List<TransactionResponseDto> getTransactionByAccountId(Long accountId) {
        log.info("Getting transaction for account : {}", accountId);
        List<Transaction> transactions = transactionRepository.findAllByAccountId(accountId);
        return transactionMapper.toTransactionResponseDtoList(transactions);
    }

    @Override
    public TransactionResponseDto getTransactionById(Long transactionId) {
        log.info("Getting transaction by id");
        Transaction transaction = transactionRepository.findById(transactionId).orElseThrow(() -> new NotFoundException(NOT_FOUND_TRANSACTION_MESSAGE));
        return transactionMapper.toTransactionResponseDto(transaction);
    }

    @Override
    public TransactionResponseDto createTransaction(TransactionRequestDto transactionRequestDto) {
        log.info("Create transaction for account : {}", transactionRequestDto.getAccountId());

        Account account = accountRepository.findById(transactionRequestDto.getAccountId())
                .orElseThrow(() -> new NotFoundException(NOT_FOUND_TRANSACTION_MESSAGE));

        Transaction transaction = new Transaction();
        transactionMapper.updateTransactionFromDto(transactionRequestDto, transaction);
        transaction.setAccount(account);
        Transaction lastTransaction = transactionRepository.findLastTransactionByAccount(transactionRequestDto.getAccountId()).orElse(null);
        BigDecimal resultAmount;
        if (lastTransaction != null) {
            resultAmount = lastTransaction.getResultAmount().add(transactionRequestDto.getTransactionAmount());
        } else {
            resultAmount = account.getInitialAmount().add(transactionRequestDto.getTransactionAmount());
        }
        if (BigDecimal.ZERO.compareTo(resultAmount) > 0) {
            throw new BadRequestCustomException(AMOUNT_EXCEDED_MESSAGE);
        }

        transaction.setResultAmount(resultAmount);
        transaction.setTransactionType(getCreditType(transactionRequestDto.getTransactionAmount()));
        transaction.setTransactionDate(Date.from(Instant.now()));
        transaction = transactionRepository.save(transaction);
        return transactionMapper.toTransactionResponseDto(transaction);
    }


    @Override
    public TransactionResponseDto updateTransaction(Long transactionId, TransactionRequestDto transactionRequestDto) {
        log.info("Update transaction for account : {}", transactionRequestDto.getAccountId());
        Transaction dbTransaction = transactionRepository.findById(transactionId).orElseThrow(() -> new NotFoundException(NOT_FOUND_TRANSACTION_MESSAGE));
        Transaction lastTransaction = transactionRepository.findLastTransactionByAccount(transactionRequestDto.getAccountId())
                .orElseThrow(() -> new BadRequestCustomException(INVALID_ACCOUNT_MESSAGE));
        if (!Objects.equals(dbTransaction.getId(), lastTransaction.getId())) {
            throw new RuntimeException(ONLY_LAST_TRANSACTION_MESSAGE);
        }
        BigDecimal prevAmount = dbTransaction.getResultAmount().subtract(dbTransaction.getTransactionAmount());
        BigDecimal resultAmount = prevAmount.add(transactionRequestDto.getTransactionAmount());
        if (BigDecimal.ZERO.compareTo(resultAmount) > 0) {
            throw new BadRequestCustomException(AMOUNT_EXCEDED_MESSAGE);
        }
        transactionMapper.updateTransactionFromDto(transactionRequestDto, dbTransaction);
        dbTransaction.setTransactionType(getCreditType(transactionRequestDto.getTransactionAmount()));
        transactionRepository.save(dbTransaction);
        return transactionMapper.toTransactionResponseDto(dbTransaction);
    }

    private String getCreditType(BigDecimal transactionAmount) {
        return BigDecimal.ZERO.compareTo(transactionAmount) > 0 ? DEBIT_CARD_TYPE : CREDIT_CARD_TYPE;
    }
}
