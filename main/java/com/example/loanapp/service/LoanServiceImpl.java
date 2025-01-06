package com.example.loanapp.service;

import com.example.loanapp.domain.Loan;
import com.example.loanapp.respository.LoanRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
public class LoanServiceImpl implements LoanService {
    @Autowired
    private LoanRepository loanRepository;

    public Loan applyForLoan(Loan loan) {
        // Example of dynamic interest rate calculation
        if (loan.getAmount().compareTo(new BigDecimal(10000)) > 0) {
            loan.setInterestRate(new BigDecimal("8.5"));
        } else {
            loan.setInterestRate(new BigDecimal("10.0"));
        }
        return loanRepository.save(loan);
    }

    public List<Loan> getLoansByUser(Long userId) {
        return loanRepository.findByUserId(userId);
    }



    public Loan rejectLoan(Long loanId) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan not found"));

        loan.setStatus(Loan.LoanStatus.REJECTED);
        return loanRepository.save(loan);
    }

    public Loan repayLoan(Long loanId) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan not found"));

        loan.setStatus(Loan.LoanStatus.REPAID);
        return loanRepository.save(loan);
    }

}
