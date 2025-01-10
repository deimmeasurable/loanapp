package com.example.loanapp.service;

import com.example.loanapp.data.TransactionResponse;
import com.example.loanapp.domain.*;
import com.example.loanapp.exception.LoanException;
import com.example.loanapp.respository.LoanRepository;
import com.example.loanapp.respository.TransactionRepository;
import com.example.loanapp.respository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
@Autowired
 private UserRepository userRepository;
@Autowired
private LoanRepository loanRepository;
@Override
public TransactionResponse recordDisbursement(Loan loan) {
    TransactionResponse transactionResponse = new TransactionResponse();
    try {
        Optional<User> user = userRepository.findUserById(loan.getUser().getId());
        if (user.isPresent()) {
            // Validate disbursement amount
            if (loan.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
                throw new LoanException("Disbursement amount must be positive.");
            }
            if (Objects.equals(loan.getUser().getId(), user.get().getId()) && loan.getStatus() == LoanStatus.APPROVED) {
                loan.setDisburseAmount(loan.getAmount());

BigDecimal disburseAmount;
                if (loan.getAmount().compareTo(BigDecimal.valueOf(1000)) > 0 && loan.getAmount().compareTo(BigDecimal.valueOf(9000)) <= 0) {
                    disburseAmount=calculateTotalAmountAfterWeek(loan.getAmount(), BigDecimal.valueOf(loan.getInterestRate()));
                } else if (loan.getAmount().compareTo(BigDecimal.valueOf(10000)) > 0 && loan.getAmount().compareTo(BigDecimal.valueOf(50000)) <= 0) {
                    disburseAmount=calculateTotalAmountAfterMonth(loan.getAmount(), BigDecimal.valueOf(loan.getInterestRate()));
                } else {
                    throw new LoanException("Loan amount is not within the acceptable range.");
                }

                // Set user and transaction details
                loan.setUser(user.get());
                Transaction transaction = new Transaction();
                transaction.setAmount(loan.getAmount());

                transaction.setDisburseAmount(disburseAmount);

                transaction.setTimestamp(LocalDateTime.now());
                transaction.setLoan(loan);
                transaction.setTransactionType(TransactionType.DISBURSEMENT);

                transactionRepository.save(transaction);

                transactionResponse.setMessage("Recorded disbursement successfully.");
            } else {
                throw new LoanException("Loan is not approved or user ID mismatch.");
            }
        } else {
            throw new LoanException("User ID does not exist.");
        }
    } catch (LoanException e) {
        transactionResponse.setMessage(e.getMessage());
    }

    return transactionResponse;
}
    @Override
    public List<Transaction> getTransactionsForLoan(Long loanId) {
        return transactionRepository.findTransactionById(loanId);
    }
    public TransactionResponse recordRepayment(Transaction loan) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            Optional<User> user = userRepository.findUserById(loan.getLoan().getUser().getId());
            if (user.isPresent()) {
                // Validate disbursement amount
                if (loan.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
                    throw new LoanException("Disbursement amount must be positive.");
                }
                if (Objects.equals(loan.getLoan().getUser().getId(), user.get().getId()) && loan.getTransactionType() == TransactionType.DISBURSEMENT) {
                    loan.setDisburseAmount(loan.getAmount());



        if (loan.getDisburseAmount().compareTo(loan.getRepayment()) > 0) {
            throw new LoanException("Repayment amount cannot exceed available balance.");
        }

        loan.setDisburseAmount(loan.getDisburseAmount()); // Update repaid amount
        Transaction transaction = new Transaction();
        transaction.setAmount(loan.getAmount());

                    transaction.setTimestamp(LocalDateTime.now());
                    transaction.setLoan(loan.getLoan());
                    transaction.setTransactionType(TransactionType.REPAYMENT);

                    transactionRepository.save(transaction);

                    transactionResponse.setMessage("Recorded repayment successfully.");
                } else {
                    throw new LoanException("Loan is not approved or user ID mismatch.");
                }
            } else {
                throw new LoanException("User ID does not exist.");
            }
        } catch (LoanException e) {
            transactionResponse.setMessage(e.getMessage());
        }

        return transactionResponse;
    }
    private static BigDecimal calculateTotalAmountAfterWeek(BigDecimal principal, BigDecimal annualInterestRate) {
        // Calculate interest for 1 week
        BigDecimal interestForWeek = calculateInterest(principal, annualInterestRate, 1, ChronoUnit.WEEKS);

        // Calculate total amount
        BigDecimal amount=principal.add(interestForWeek);
log.info("interest value : {}",interestForWeek);
       return amount;
    }
    private static BigDecimal calculateTotalAmountAfterMonth(BigDecimal principal, BigDecimal annualInterestRate) {
        // Calculate interest for 1 week
        BigDecimal interestForMonth = calculateInterest(principal, annualInterestRate, 1, ChronoUnit.MONTHS);
        log.info("interest value : {}",interestForMonth);
        // Calculate total amount
BigDecimal amount=principal.add(interestForMonth);

        return amount;
    }

    private static BigDecimal calculateInterest(BigDecimal principal, BigDecimal annualInterestRate, long period, ChronoUnit unit) {
        // Calculate the interest for the specified period
        BigDecimal interest = principal.multiply(annualInterestRate).multiply(BigDecimal.valueOf(period)).divide(BigDecimal.valueOf(1), 2, RoundingMode.HALF_UP);

        // Adjust the interest calculation based on the time unit
        if (unit == ChronoUnit.MONTHS) {
            interest = interest.divide(BigDecimal.valueOf(12), 2, RoundingMode.HALF_UP);
        } else if (unit == ChronoUnit.WEEKS) {
            interest = interest.divide(BigDecimal.valueOf(52), 2, RoundingMode.HALF_UP);
        } else if (unit == ChronoUnit.YEARS) {
            // No adjustment needed for years
            interest = interest.divide(BigDecimal.valueOf(365), 2, RoundingMode.HALF_UP);
        }

        return interest.setScale(2, RoundingMode.HALF_UP);
    }
}

