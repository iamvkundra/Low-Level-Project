package org.example.service;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import org.example.enums.Coin;
import org.example.model.Inventory;
import org.example.vendingstates.State;
import org.example.vendingstates.impl.IdleState;

public class VendingMachineService {

    @Setter
    @Getter
    public State vendingMachineState;

    @Getter
    @Setter
    private Inventory inventory;

    @Getter
    @Setter
    private List<Coin> coins;

    public VendingMachineService() {
        this.vendingMachineState = new IdleState();
        this.inventory = new Inventory();
        this.coins = new ArrayList<>();
    }

    public List<Coin> getCoinList() {
        return coins;
    }

}
