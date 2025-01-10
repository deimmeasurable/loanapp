package com.example.loanapp.service;

import com.example.loanapp.data.LoanRequest;
import com.example.loanapp.data.LoanResponse;
import com.example.loanapp.domain.Loan;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LoanService {
    LoanResponse applyForLoan(LoanRequest loanRequest);
    LoanResponse rejectLoan(Long loanId);
    LoanResponse repayLoan(Long loanId);
    List<Loan> getLoansByUser(Long userId);
    LoanResponse approveLoan(Long loanId);
}
