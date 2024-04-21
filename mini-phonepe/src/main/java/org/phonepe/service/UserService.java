package org.phonepe.service;

import static org.phonepe.Utils.Validation.checkUserData;

import org.phonepe.model.User;
import org.phonepe.model.Wallet;
import org.phonepe.repository.UserRespository;

public class UserService {

    private UserRespository userRespository;

    private WalletService walletService;

    public UserService(UserRespository userRespository, WalletService walletService) {
        this.walletService = walletService;
        this.userRespository = userRespository;
    }

    public User register(User user) {
        if(!checkUserData(user)) {
            throw  new RuntimeException("For given user the userId/userName is not provided.");
        }
        boolean isUserRegistered = userRespository.registerUser(user);
        if (!isUserRegistered) {
            throw  new RuntimeException("Looks like user already exist");
        }
        Wallet wallet = walletService.createWallet(user);
        System.out.println("User is Registered and now created wallet for user: "+user.getUserId());
        return user;
    }

    public User getUserById(String userId) {
        if (userId == null) {
            throw new RuntimeException("Invalid userId provided");
        }
        User user  = userRespository.getUserById(userId);
        return user;
    }
}
