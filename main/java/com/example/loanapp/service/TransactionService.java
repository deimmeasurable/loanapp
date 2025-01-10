package com.example.loanapp.service;

import com.example.loanapp.data.TransactionResponse;
import com.example.loanapp.domain.Loan;
import com.example.loanapp.domain.Transaction;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionService {
    TransactionResponse recordDisbursement(Loan loan);
    TransactionResponse recordRepayment(Transaction loan);
    List<Transaction> getTransactionsForLoan(Long loanId);
}
