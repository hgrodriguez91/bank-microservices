package com.devsu.account.entity;

import java.math.BigDecimal;
import java.util.Date;

public interface AccountTransactionProjection {
    Date getTransactionDate();

    String getAccountNumber();

    String getAccountType();

    BigDecimal getInitialAmount();

    BigDecimal getTransactionAmount();

    BigDecimal getResultantAmount();

    Boolean getStatus();
}
