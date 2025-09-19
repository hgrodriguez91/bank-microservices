package com.devsu.account.repository;

import com.devsu.account.entity.Account;
import com.devsu.account.entity.AccountTransactionProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findAllByClientId(Long clientId);

    @Query(value = """
            select
            a.accountNumber as accountNumber
            ,a.initialAmount as initialAmount
            ,a.accountType as accountType
            ,a.status as status
            ,t.transactionDate as transactionDate
            ,t.transactionAmount as transactionAmount
            ,t.resultAmount as resultantAmount
            from Account a
             inner join Transaction t
             on t.account.id = a.id
            where a.clientId = :clientId
            and t.transactionDate between :initDate and :endDate
            """)
    List<AccountTransactionProjection> getAccountTransactionHistory(@Param("clientId") Long clientId, @Param("initDate") Date initDate, @Param("endDate") Date endDate);
}
