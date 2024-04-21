package org.phonepe.SortStratergy;

import java.util.Comparator;
import java.util.List;

import org.phonepe.model.Ordering;
import org.phonepe.model.Transaction;

public class AmountSortStatergy implements TransactionSort{
    @Override
    public void sort(List<Transaction> transactions, Ordering order) {
        transactions.sort(new Comparator<Transaction>() {
            @Override
            public int compare(Transaction o1, Transaction o2) {
                if (order == Ordering.ASCENDING) {
                    return o1.getTransactionAmount().compareTo(o2.getTransactionAmount());
                }else {
                    return o2.getTransactionAmount().compareTo(o1.getTransactionAmount());
                }
            }
        });
    }
}
