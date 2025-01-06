package com.example.loanapp.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;
    private int tenure; // in months
    private BigDecimal interestRate;


    @ManyToOne
    private User user;

    // New field for approval status
    @Enumerated(EnumType.STRING)
    private LoanStatus status;

}
