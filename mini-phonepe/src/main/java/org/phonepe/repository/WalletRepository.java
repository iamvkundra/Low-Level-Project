package org.phonepe.repository;

import java.util.HashMap;

import org.phonepe.model.User;
import org.phonepe.model.Wallet;

public class WalletRepository {
    HashMap<String, Wallet> walletDataByWalletId = new HashMap<>();
    HashMap<String, String> userIdToWalletId = new HashMap<>();

    public boolean createWallet(User user, Wallet wallet) {
        if (!userIdToWalletId.containsKey(user.getUserId())) {
            userIdToWalletId.put(user.getUserId(), wallet.getWalletId());
            if (!walletDataByWalletId.containsKey(wallet.getWalletId())) {
                walletDataByWalletId.put(wallet.getWalletId(), wallet);
            }else {
                return false;
            }
        }else {
            return false;
        }
        return true;
    }

    public Wallet fetchWallet(String userId) {
        String walletId = userIdToWalletId.get(userId);
        if(walletId == null) {
            return null;
        }
        return walletDataByWalletId.get(walletId);
    }
}
