package com.example.loanapp.service;

import com.example.loanapp.domain.Loan;
import com.example.loanapp.domain.Transaction;
import com.example.loanapp.respository.TransactionRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction recordDisbursement(Loan loan, BigDecimal amount) {
        // Validate disbursement amount
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Disbursement amount must be positive.");
        }

        loan.setDisbursedAmount(loan.getDisbursedAmount().add(amount)); // Update disbursed amount
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setTimestamp(LocalDateTime.now());
        transaction.setLoan(loan);
        transaction.setType(Transaction.TransactionType.DISBURSEMENT);
        transactionRepository.save(transaction);
        return transaction;
    }
    public List<Transaction> getTransactionsForLoan(Long loanId) {
        return transactionRepository.findById(loanId);
    }
    public Transaction recordRepayment(Loan loan, BigDecimal amount) {
        // Validate repayment amount
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Repayment amount must be positive.");
        }

        // Ensure repayment does not exceed available balance
        if (amount.compareTo(loan.getAvailableBalance()) > 0) {
            throw new IllegalArgumentException("Repayment amount cannot exceed available balance.");
        }

        loan.setRepaidAmount(loan.getRepaidAmount().add(amount)); // Update repaid amount
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setTimestamp(LocalDateTime.now());
        transaction.setLoan(loan);
        transaction.setType(Transaction.TransactionType.REPAYMENT);
        transactionRepository.save(transaction);
        return transaction;
    }
}
