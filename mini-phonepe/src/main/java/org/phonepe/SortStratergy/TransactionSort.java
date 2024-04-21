package org.phonepe.SortStratergy;

import java.util.List;

import org.phonepe.model.Ordering;
import org.phonepe.model.Transaction;

public interface TransactionSort {
    void sort(List<Transaction> transactions, Ordering order);
}
