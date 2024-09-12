package com.platformtrasnport.platformtransport.controller;

import com.platformtrasnport.platformtransport.model.Transaction;
import com.platformtrasnport.platformtransport.model.Utilisateur;
import com.platformtrasnport.platformtransport.repository.TransactionRepository;
import com.platformtrasnport.platformtransport.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/transactions")
@CrossOrigin(origins = "http://localhost:4200")
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction) {
        // Validate and set the user if needed
        if (transaction.getEmploye() != null) {
            Optional<Utilisateur> utilisateur = utilisateurRepository.findById(transaction.getEmploye().getId());
            if (utilisateur.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }

        Transaction savedTransaction = transactionRepository.save(transaction);
        return new ResponseEntity<>(savedTransaction, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Long id) {
        Optional<Transaction> transaction = transactionRepository.findById(id);
        if (transaction.isPresent()) {
            return new ResponseEntity<>(transaction.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<Iterable<Transaction>> getAllTransactions() {
        Iterable<Transaction> transactions = transactionRepository.findAll();
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transaction> updateTransaction(@PathVariable Long id, @RequestBody Transaction updatedTransaction) {
        Optional<Transaction> transaction = transactionRepository.findById(id);
        if (transaction.isPresent()) {
            Transaction existingTransaction = transaction.get();
            existingTransaction.setMontant(updatedTransaction.getMontant());
            existingTransaction.setDate(updatedTransaction.getDate());
            existingTransaction.setEmploye(updatedTransaction.getEmploye());
            Transaction savedTransaction = transactionRepository.save(existingTransaction);
            return new ResponseEntity<>(savedTransaction, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
        if (transactionRepository.existsById(id)) {
            transactionRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}