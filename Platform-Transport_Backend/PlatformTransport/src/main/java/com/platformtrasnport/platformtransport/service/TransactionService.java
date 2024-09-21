package com.platformtrasnport.platformtransport.service;

import com.platformtrasnport.platformtransport.dto.TransactionDto;

import java.util.List;
import java.util.Optional;

public interface TransactionService {
    TransactionDto createTransaction(TransactionDto transactionDto);
    Optional<TransactionDto> getTransactionById(Long id);
    List<TransactionDto> getAllTransactions();
    TransactionDto updateTransaction(Long id, TransactionDto updatedTransactionDto);
    void deleteTransaction(Long id);
}