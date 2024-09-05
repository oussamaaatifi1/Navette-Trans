package com.platformtrasnport.platformtransport.service.Impl;

import com.platformtrasnport.platformtransport.model.Transaction;
import com.platformtrasnport.platformtransport.model.Utilisateur;
import com.platformtrasnport.platformtransport.repository.TransactionRepository;
import com.platformtrasnport.platformtransport.repository.UtilisateurRepository;
import com.platformtrasnport.platformtransport.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Override
    public Transaction createTransaction(Transaction transaction) {
        if (transaction.getEmploye() != null) {
            Optional<Utilisateur> utilisateur = utilisateurRepository.findById(transaction.getEmploye().getId());
            if (utilisateur.isEmpty()) {
                throw new IllegalArgumentException("Utilisateur not found");
            }
        }
        return transactionRepository.save(transaction);
    }

    @Override
    public Optional<Transaction> getTransactionById(Long id) {
        return transactionRepository.findById(id);
    }

    @Override
    public Iterable<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public Transaction updateTransaction(Long id, Transaction updatedTransaction) {
        if (!transactionRepository.existsById(id)) {
            throw new IllegalArgumentException("Transaction not found");
        }
        updatedTransaction.setId(id);
        return transactionRepository.save(updatedTransaction);
    }

    @Override
    public void deleteTransaction(Long id) {
        if (!transactionRepository.existsById(id)) {
            throw new IllegalArgumentException("Transaction not found");
        }
        transactionRepository.deleteById(id);
    }
}
