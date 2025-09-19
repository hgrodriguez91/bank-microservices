package com.devsu.account.repository;

import com.devsu.account.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findAllByAccountId(Long accountId);
    @Query(value = "select t from Transaction t  where t.account.id = :accountId order by t.transactionDate desc limit 1")
    Optional<Transaction> findLastTransactionByAccount(@Param("accountId") Long accountId);
}
