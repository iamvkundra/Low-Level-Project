package org.phonepe.model;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Wallet {
    String walletId;
    Double balanceAmount;

    // Constructor to generate ID automatically
    public Wallet(Double balanceAmount) {
        this.walletId = UUID.randomUUID().toString();
        this.balanceAmount = balanceAmount;
    }
}
