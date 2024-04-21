package org.phonepe;

import java.util.ArrayList;

import org.phonepe.SortStratergy.AmountSortStatergy;
import org.phonepe.enums.PaymentMode;
import org.phonepe.model.Ordering;
import org.phonepe.model.User;
import org.phonepe.repository.TransactionRepository;
import org.phonepe.repository.UserRespository;
import org.phonepe.repository.WalletRepository;
import org.phonepe.service.Payment;
import org.phonepe.service.PaymentService;
import org.phonepe.service.TransactionService;
import org.phonepe.service.UserService;
import org.phonepe.service.WalletService;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.println("STARTING MINI_PHONEPE");

        UserRespository userRespository = new UserRespository();
        WalletRepository walletRepository = new WalletRepository();
        TransactionRepository transactionRepository = new TransactionRepository();

        WalletService walletService = new WalletService(walletRepository);
        UserService userService = new UserService(userRespository, walletService);
        TransactionService transactionService = new TransactionService(transactionRepository);

        Payment payment = new PaymentService(userService, walletService, transactionService);

        User vikas = new User("Vikas");
        User mayank = new User("Mayank");
        User pratibha = new User("Pratibha");

        userService.register(vikas);
        userService.register(mayank);
        userService.register(pratibha);

        payment.topUpWallet(mayank.getUserId(), 1000.0, PaymentMode.UPI);
        payment.topUpWallet(mayank.getUserId(), 12.0, PaymentMode.CREDIT_CARD);

        System.out.println(walletService.getWalletById(mayank.getUserId()));
        System.out.println(walletService.getWalletById(pratibha.getUserId()));
        System.out.println(walletService.getWalletById(vikas.getUserId()));

        payment.getTransaction(mayank.getUserId(), new AmountSortStatergy(), Ordering.ASCENDING,
                new ArrayList<>());

    }
}