package org.example.vendingstates.impl;

import java.util.List;

import org.example.enums.Coin;
import org.example.enums.Item;
import org.example.service.VendingMachineService;
import org.example.vendingstates.State;

public class IdleState implements State {

    public IdleState() {
    }

    @Override
    public void chickOnInsertCoinButton(VendingMachineService vendingMachineService) throws Exception {
        vendingMachineService.setVendingMachineState(new HasMoneyState());
    }

    @Override
    public void chickOnProductSelectionButton(VendingMachineService vendingMachineService) throws Exception {
        throw new Exception("First you need to chick on insert coin button");
    }

    @Override
    public void insertCoin(VendingMachineService vendingMachineService, Coin coins) throws Exception {
        throw new Exception("you can not insert coin in idle state");
    }

    @Override
    public void chooseProduct(VendingMachineService vendingMachineService, int productCode) throws Exception {
        throw new Exception("you cannot choose product in idle state");
    }

    @Override
    public int getChange(int returnChangeMoney) throws Exception {
        throw new Exception("you can not get change in idle state");
    }

    @Override
    public Item dispenseProduct(VendingMachineService vendingMachineService, int productCode) throws Exception {
        throw new Exception("product can not be dispensed idle state");
    }

    @Override
    public List<Coin> refundFullMoney(VendingMachineService vendingMachineService) throws Exception {
        throw new Exception("you can not get refunded in idle state");
    }

    @Override
    public void updateInventory(VendingMachineService vendingMachineService, Item item, int codeNumber) throws Exception {
        vendingMachineService.getInventory();
    }
}
