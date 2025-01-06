package com.example.loanapp.service;

import com.example.loanapp.domain.Loan;
@Service
public interface LoanService {
    Loan applyForLoan(Loan loan);
    Loan rejectLoan(Long loanId);
    Loan repayLoan(Long loanId);
}
