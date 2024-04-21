package org.phonepe.FilterStrategy;

import java.util.List;

import org.phonepe.model.Transaction;

public interface TransactionFilterStrategy {

    List<Transaction> filter(List<Transaction> transactions);
}
