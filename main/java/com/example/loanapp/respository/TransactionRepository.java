package com.example.loanapp.respository;

import com.example.loanapp.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Transaction findById(Long id);
}
