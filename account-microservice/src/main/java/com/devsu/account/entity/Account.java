package com.devsu.account.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Account implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String accountNumber;
    private String accountType;
    private BigDecimal initialAmount;
    private Boolean status;
    @Column(name = "client_id")
    private Long clientId;
    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Transaction> transactions;
}
