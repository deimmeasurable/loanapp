package com.example.loanapp.service;

import com.example.loanapp.data.LoanRequest;
import com.example.loanapp.data.LoanResponse;
import com.example.loanapp.domain.Loan;
import com.example.loanapp.domain.LoanStatus;
import com.example.loanapp.exception.LoanException;
import com.example.loanapp.respository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class LoanServiceImpl implements LoanService {
    @Autowired
    private LoanRepository loanRepository;
    @Override
    public LoanResponse applyForLoan(LoanRequest loanRequest) {
        LoanRequest loan= LoanRequest.builder()
                .user(loanRequest.getUser())
                .id(loanRequest.getId())
                .tenure(loanRequest.getTenure())
                .amount(loanRequest.getAmount())
                .interestRate(loanRequest.getInterestRate())
                .status(loanRequest.getStatus())

                .build();


        Loan savedLoan = new Loan();
        savedLoan.setUser(loan.getUser());
        savedLoan.setAmount(loan.getAmount());
        savedLoan.setStatus(loan.getStatus());
        savedLoan.setInterestRate(loan.getInterestRate());
        savedLoan.setStatus(LoanStatus.PENDING);
        savedLoan.setTenure(loanRequest.getTenure());


        LoanResponse loanResonse = LoanResponse.builder()
                .message("loan created")
                .build();

        return loanResonse;
    }
    @Override
    public List<Loan> getLoansByUser(Long userId) {
        return loanRepository.findByUserId(userId);
    }


    @Override
    public LoanResponse rejectLoan(Long loanId) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new LoanException("Loan not found"));

        loan.setStatus(LoanStatus.REJECTED);
        loanRepository.save(loan);
        LoanResponse loanResonse = LoanResponse.builder()
                .message("loan Rejected")
                .build();
        return loanResonse;
    }
    @Override
    public LoanResponse repayLoan(Long loanId) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new LoanException("Loan not found"));

        loan.setStatus(LoanStatus.REPAID);
       loanRepository.save(loan);
        LoanResponse loanResonse = LoanResponse.builder()
                .message("loan Repaid")
                .build();
        return loanResonse;
    }
    @Override
    public LoanResponse approveLoan(Long loanId) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new LoanException("Loan not found"));

        loan.setStatus(LoanStatus.REPAID);
        loanRepository.save(loan);
        LoanResponse loanResonse = LoanResponse.builder()
                .message("loan approved")
                .build();
        return loanResonse;
    }
}
