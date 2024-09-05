package com.platformtrasnport.platformtransport.service;

import com.platformtrasnport.platformtransport.model.Transaction;

import java.util.Optional;

public interface TransactionService {
    Transaction createTransaction(Transaction transaction);
    Optional<Transaction> getTransactionById(Long id);
    Iterable<Transaction> getAllTransactions();
    Transaction updateTransaction(Long id, Transaction updatedTransaction);
    void deleteTransaction(Long id);
}
