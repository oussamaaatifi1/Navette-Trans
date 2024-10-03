package com.platformtrasnport.platformtransport.service.impl;

import com.platformtrasnport.platformtransport.dto.TransactionDto;
import com.platformtrasnport.platformtransport.mapper.TransactionMapper;
import com.platformtrasnport.platformtransport.model.Transaction;
import com.platformtrasnport.platformtransport.model.Utilisateur;
import com.platformtrasnport.platformtransport.repository.TransactionRepository;
import com.platformtrasnport.platformtransport.repository.UtilisateurRepository;
import com.platformtrasnport.platformtransport.service.TransactionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;

    private final UtilisateurRepository utilisateurRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository, UtilisateurRepository utilisateurRepository) {
        this.transactionRepository = transactionRepository;
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public TransactionDto createTransaction(TransactionDto transactionDto) {
        if (transactionDto.getEmployeId() != null) {
            Optional<Utilisateur> utilisateur = utilisateurRepository.findById(transactionDto.getEmployeId());
            if (utilisateur.isEmpty()) {
                throw new IllegalArgumentException("Utilisateur not found");
            }
        }
        Transaction transaction = TransactionMapper.INSTANCE.dtoToTransaction(transactionDto);
        Transaction savedTransaction = transactionRepository.save(transaction);
        return TransactionMapper.INSTANCE.transactionToDto(savedTransaction);
    }

    @Override
    public Optional<TransactionDto> getTransactionById(Long id) {
        return transactionRepository.findById(id)
                .map(TransactionMapper.INSTANCE::transactionToDto);
    }

    @Override
    public List<TransactionDto> getAllTransactions() {
        return transactionRepository.findAll().stream()
                .map(TransactionMapper.INSTANCE::transactionToDto)
                .collect(Collectors.toList());
    }

    @Override
    public TransactionDto updateTransaction(Long id, TransactionDto updatedTransactionDto) {
        if (!transactionRepository.existsById(id)) {
            throw new IllegalArgumentException("Transaction not found");
        }
        updatedTransactionDto.setId(id);
        Transaction transaction = TransactionMapper.INSTANCE.dtoToTransaction(updatedTransactionDto);
        Transaction updatedTransaction = transactionRepository.save(transaction);
        return TransactionMapper.INSTANCE.transactionToDto(updatedTransaction);
    }

    @Override
    public void deleteTransaction(Long id) {
        if (!transactionRepository.existsById(id)) {
            throw new IllegalArgumentException("Transaction not found");
        }
        transactionRepository.deleteById(id);
    }
}