package org.phonepe.service;

import java.util.List;

import org.phonepe.FilterStrategy.TransactionFilterStrategy;
import org.phonepe.SortStratergy.TransactionSort;
import org.phonepe.enums.PaymentMode;
import org.phonepe.model.Ordering;
import org.phonepe.model.Transaction;

public interface Payment {
    Transaction payment(String payerId, String payeeId, Double amount, PaymentMode mode);

    Transaction topUpWallet(String userId, Double amount, PaymentMode paymentMode);

    List<Transaction> getTransaction(String userId, TransactionSort transactionSort, Ordering order,
                                     List<TransactionFilterStrategy> transactionFilterStrategies);
}


