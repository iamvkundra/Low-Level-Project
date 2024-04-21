package org.phonepe.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.phonepe.FilterStrategy.TransactionFilterStrategy;
import org.phonepe.SortStratergy.TransactionSort;
import org.phonepe.enums.PaymentMode;
import org.phonepe.enums.PaymentType;
import org.phonepe.model.Ordering;
import org.phonepe.model.Transaction;
import org.phonepe.model.User;
import org.phonepe.model.Wallet;

public class PaymentService implements Payment {

    private final UserService userService;
    private final WalletService walletService;

    private final TransactionService transactionService;

    private Date date = new Date();

    public PaymentService(UserService userService, WalletService walletService,
                          TransactionService transactionService) {
        this.userService = userService;
        this.walletService = walletService;
        this.transactionService = transactionService;
    }

    @Override
    public Transaction payment(String payerId, String payeeId, Double amount, PaymentMode mode) {
        User payerUser = userService.getUserById(payerId);
        User payeeUser = userService.getUserById(payeeId);

        if(payeeUser == null || payerUser == null) {
            throw new RuntimeException("No user Found");
        }

        Wallet payerWallet = walletService.getWalletById(payerUser.getUserId());
        Wallet payeeWallet = walletService.getWalletById(payeeUser.getUserId());


        return  null;
    }

    @Override
    public Transaction topUpWallet(String userId, Double amount, PaymentMode paymentMode) {
        User user = userService.getUserById(userId);
        if(user == null) {
            throw new RuntimeException("Invalid user");
        }
        //TODO: Add cashback logic.
        Wallet wallet = walletService.getWalletById(user.getUserId());
        wallet.setBalanceAmount(wallet.getBalanceAmount() + amount);

        Transaction transaction = new Transaction(userId, userId, amount, paymentMode, PaymentType.TOP_UP
        , date.getDate());

        transactionService.addTransaction(userId, transaction);
        return transaction;
    }

    @Override
    public List<Transaction> getTransaction(String userId, TransactionSort transactionSort,
                                            Ordering order,
                                            List<TransactionFilterStrategy> transactionFilterStrategies) {
        List<Transaction> transactionList = transactionService.getTransactionListByUserId(userId);
        if (Objects.nonNull(transactionSort)) {
            transactionSort.sort(transactionList, order);
        }
        if(Objects.nonNull(transactionFilterStrategies)) {
            for (TransactionFilterStrategy filterStrategy : transactionFilterStrategies) {
                transactionList = filterStrategy.filter(transactionList);
            }
        }
        System.out.println();
        System.out.println("Transaction Records for user: "+userId);
        System.out.println(transactionList);
        return transactionList == null ? new ArrayList<>() : transactionList;
    }
}
