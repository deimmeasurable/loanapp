package com.example.loanapp.respository;

import com.example.loanapp.domain.Loan;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    List<Loan> findByUserId(Long userId);

    Loan findLoanById(Long id);
}
