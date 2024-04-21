package org.phonepe.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.phonepe.model.Transaction;

public class TransactionRepository {

    private HashMap<String, List<Transaction>> transactionList = new HashMap<>();

    public TransactionRepository() { }

    public void updateTransaction(String userId, Transaction transaction) {
        if (!transactionList.containsKey(userId)) {
            transactionList.put(userId, new ArrayList<>());
        }
        transactionList.get(userId).add(transaction);
    }

    public List<Transaction> getTransactionByUserId(String userId) {
        return transactionList.get(userId);
    }

}
