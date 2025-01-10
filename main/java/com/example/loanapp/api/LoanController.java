package com.example.loanapp.api;

import com.example.loanapp.data.LoanRequest;
import com.example.loanapp.data.LoanResponse;
import com.example.loanapp.domain.Loan;
import com.example.loanapp.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/loan")
public class LoanController {
    @Autowired
    private LoanService loanService;



    @PostMapping
    public LoanResponse applyForLoan(@RequestBody LoanRequest loan) {
        return loanService.applyForLoan(loan);
    }

    @PutMapping("/{loanId}/approve")
    public LoanResponse approveLoan(@PathVariable Long loanId) {
        return loanService.approveLoan(loanId);
    }

    @PutMapping("/{loanId}/reject")
    public LoanResponse rejectLoan(@PathVariable Long loanId) {
        return loanService.rejectLoan(loanId);
    }

    @PutMapping("/{loanId}/repay")
    public LoanResponse repayLoan(@PathVariable Long loanId) {
        return loanService.repayLoan(loanId);
    }

    @GetMapping("/user/{userId}")
    public List<Loan> getLoansByUser(@PathVariable Long userId) {
        return loanService.getLoansByUser(userId);
    }
}
