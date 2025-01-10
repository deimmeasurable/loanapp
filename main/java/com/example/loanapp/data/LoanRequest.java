package com.example.loanapp.data;

import com.example.loanapp.domain.LoanStatus;
import com.example.loanapp.domain.User;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoanRequest {
    private Long id;

    private BigDecimal amount;
    private int tenure; // in months
    private Double interestRate;
    private BigDecimal disburseAmount;
    @ManyToOne
    private User user;

    // New field for approval status
    @Enumerated(EnumType.STRING)
    private LoanStatus status;
}
