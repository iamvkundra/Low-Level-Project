package org.example.vendingstates.impl;

import java.util.List;

import org.example.enums.Coin;
import org.example.enums.Item;
import org.example.service.VendingMachineService;
import org.example.vendingstates.State;

public class SelectionState implements State {
    @Override
    public void chickOnInsertCoinButton(VendingMachineService vendingMachineService) throws Exception {
        throw new Exception("already coin is inserted");
    }

    @Override
    public void chickOnProductSelectionButton(VendingMachineService vendingMachineService) throws Exception {
        return;
    }

    @Override
    public void insertCoin(VendingMachineService vendingMachineService, Coin coins) throws Exception {
        throw new Exception("you cannot insert coin in selection state");
    }

    @Override
    public void chooseProduct(VendingMachineService vendingMachineService, int productCode) throws Exception {
        Item item = vendingMachineService.getInventory().getItem(productCode);

        if (item == null) {
            throw new Exception("The product is unavailable, Cannot choose this product");
        }

        int totalAmountPaidByUser = 0;
        for (Coin coin : vendingMachineService.getCoinList()) {
            totalAmountPaidByUser += coin.getValue();
        }

        if (totalAmountPaidByUser < item.getPrice()) {
            System.out.println("Insufficient Amount, Product you selected is for price: " + item.getPrice() + " and you paid: " + totalAmountPaidByUser);
            refundFullMoney(vendingMachineService);
            throw new Exception("Insufficient amount");
        }else {
            if (totalAmountPaidByUser > item.getPrice()) {
                getChange(totalAmountPaidByUser- item.getPrice());
            }
            vendingMachineService.setVendingMachineState(new DispenceProductState(vendingMachineService, productCode));
        }
    }

    @Override
    public int getChange(int returnChangeMoney) throws Exception {
        System.out.println("Returned the change in the coin dispense tray: "+returnChangeMoney);
        return returnChangeMoney;
    }

    @Override
    public Item dispenseProduct(VendingMachineService vendingMachineService, int productCode) throws Exception {
        throw new Exception("cannot dispense product in selection state");
    }

    @Override
    public List<Coin> refundFullMoney(VendingMachineService vendingMachineService) throws Exception {
        System.out.println("Returned the full amount back in the coin dispense tray");
        vendingMachineService.setVendingMachineState(new IdleState());
        return vendingMachineService.getCoinList();
    }

    @Override
    public void updateInventory(VendingMachineService vendingMachineService, Item item, int codeNumber) throws Exception {
        throw new Exception("cannot update the inventory in selection state");
    }
}
