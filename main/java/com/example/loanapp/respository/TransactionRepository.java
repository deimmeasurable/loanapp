package com.example.loanapp.respository;

import com.example.loanapp.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
   List<Transaction> findTransactionById(Long id);
}
