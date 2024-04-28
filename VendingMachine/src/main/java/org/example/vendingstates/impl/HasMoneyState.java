package org.example.vendingstates.impl;

import java.util.List;

import org.example.enums.Coin;
import org.example.enums.Item;
import org.example.service.VendingMachineService;
import org.example.vendingstates.State;

public class HasMoneyState implements State {

    public HasMoneyState() { }

    @Override
    public void chickOnInsertCoinButton(VendingMachineService vendingMachineService) throws Exception {
        return;
    }

    @Override
    public void chickOnProductSelectionButton(VendingMachineService vendingMachineService) throws Exception {
        System.out.println("Now you can select the product");
        vendingMachineService.setVendingMachineState(new SelectionState());
    }

    @Override
    public void insertCoin(VendingMachineService vendingMachineService, Coin coins) throws Exception {
        System.out.println("Accepted the coin : coin: "+ coins.toString());
        vendingMachineService.getCoinList().add(coins);
    }

    @Override
    public void chooseProduct(VendingMachineService vendingMachineService, int productCode) throws Exception {
        throw new Exception("you need to click on start product selection button first");
    }

    @Override
    public int getChange(int returnChangeMoney) throws Exception {
        throw new Exception("you cannot get change in hasMoney state");
    }

    @Override
    public Item dispenseProduct(VendingMachineService vendingMachineService, int productCode) throws Exception {
        throw new Exception("you cannot dispense product in hasMoney state");
    }

    @Override
    public List<Coin> refundFullMoney(VendingMachineService vendingMachineService) throws Exception {
        System.out.println("Returned the full amount back in the Coin dispense tray");
        vendingMachineService.setVendingMachineState(new IdleState());
        return vendingMachineService.getCoins();
    }

    @Override
    public void updateInventory(VendingMachineService vendingMachineService, Item item, int codeNumber) throws Exception {

    }
}
