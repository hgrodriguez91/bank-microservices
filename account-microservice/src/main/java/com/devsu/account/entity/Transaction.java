package com.devsu.account.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Transaction implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date transactionDate;
    private String transactionType;
    private BigDecimal transactionAmount;
    private BigDecimal resultAmount;
    @ManyToOne
    @JoinColumn(name = "account_id")
    @ToString.Exclude
    private Account account;

}
