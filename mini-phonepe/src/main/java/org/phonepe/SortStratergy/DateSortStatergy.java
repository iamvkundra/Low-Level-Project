package org.phonepe.SortStratergy;

import java.util.Comparator;
import java.util.List;

import org.phonepe.model.Ordering;
import org.phonepe.model.Transaction;

public class DateSortStatergy implements TransactionSort{
    @Override
    public void sort(List<Transaction> transactions, Ordering order) {
        transactions.sort(new Comparator<Transaction>() {
            @Override
            public int compare(Transaction o1, Transaction o2) {
                if (order == Ordering.ASCENDING) {
                    return o1.getDateOfPayment() - o2.getDateOfPayment();
                }else {
                    return o2.getDateOfPayment() - o1.getDateOfPayment();
                }
            }
        });
    }
}
