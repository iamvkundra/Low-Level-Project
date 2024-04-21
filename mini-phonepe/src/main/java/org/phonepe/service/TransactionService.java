package org.phonepe.service;

import java.util.ArrayList;
import java.util.List;

import org.phonepe.model.Transaction;
import org.phonepe.repository.TransactionRepository;

public class TransactionService {

    private TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public void addTransaction(String userId, Transaction transaction) {
        transactionRepository.updateTransaction(userId, transaction);
    }

    public List<Transaction> getTransactionListByUserId(String userId) {
        List<Transaction> transactionList = transactionRepository.getTransactionByUserId(userId);
        return transactionList == null ? new ArrayList<>() : transactionList;
    }
}
