package com.example.loanapp.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;
    private int tenure;
    private Double interestRate;
   private BigDecimal disburseAmount;
    @ManyToOne
    private User user;

    // New field for approval status
    @Enumerated(EnumType.STRING)
    private LoanStatus status;
    private LocalDateTime createdDate;

}
