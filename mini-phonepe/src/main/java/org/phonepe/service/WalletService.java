package org.phonepe.service;

import org.phonepe.model.User;
import org.phonepe.model.Wallet;
import org.phonepe.repository.WalletRepository;

public class WalletService {

    private WalletRepository walletRepository;
    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    public Wallet createWallet(User user) {
        Wallet wallet = new Wallet(0.0);
        boolean isWalletCreated = walletRepository.createWallet(user, wallet);
        if (!isWalletCreated) {
            throw new RuntimeException("Wallet not created");
        }
        System.out.println("Successfully wallet created for user: "+user.getUserId());
        return wallet;
    }

    public Wallet getWalletById(String userId) {
        if (userId == null) {
            throw new RuntimeException("Invalid UserId provied");
        }
        return walletRepository.fetchWallet(userId);
    }
}
