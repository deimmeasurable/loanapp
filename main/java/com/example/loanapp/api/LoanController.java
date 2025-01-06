package com.example.loanapp.api;

import com.example.loanapp.domain.Loan;
import com.example.loanapp.service.LoanService;

import java.util.List;

public class LoanController {
    @Autowired
    private LoanService loanService;

    @Autowired
    private LoanService loanService;

    @PostMapping
    public Loan applyForLoan(@RequestBody Loan loan) {
        return loanService.applyForLoan(loan);
    }

    @PutMapping("/{loanId}/approve")
    public Loan approveLoan(@PathVariable Long loanId) {
        return loanService.approveLoan(loanId);
    }

    @PutMapping("/{loanId}/reject")
    public Loan rejectLoan(@PathVariable Long loanId) {
        return loanService.rejectLoan(loanId);
    }

    @PutMapping("/{loanId}/repay")
    public Loan repayLoan(@PathVariable Long loanId) {
        return loanService.repayLoan(loanId);
    }

    @GetMapping("/user/{userId}")
    public List<Loan> getLoansByUser(@PathVariable Long userId) {
        return loanService.getLoansByUser(userId);
    }
}
