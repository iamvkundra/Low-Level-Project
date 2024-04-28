package org.example.vendingstates.impl;

import java.util.List;

import org.example.enums.Coin;
import org.example.enums.Item;
import org.example.service.VendingMachineService;
import org.example.vendingstates.State;

public class DispenceProductState implements State {

    DispenceProductState(VendingMachineService vendingMachineService, int productCode) throws Exception {
        System.out.println("Now Vending machine is in dispense state");
        dispenseProduct(vendingMachineService, productCode);
    }
    @Override
    public void chickOnInsertCoinButton(VendingMachineService vendingMachineService) throws Exception {
        throw new Exception("cannot insert coin in dispense product state");
    }

    @Override
    public void chickOnProductSelectionButton(VendingMachineService vendingMachineService) throws Exception {
        throw new Exception("product cannot be selected in dispense state");
    }

    @Override
    public void insertCoin(VendingMachineService vendingMachineService, Coin coins) throws Exception {
        throw new Exception("coin cannot be inserted in dispense product state");
    }

    @Override
    public void chooseProduct(VendingMachineService vendingMachineService, int productCode) throws Exception {
        throw new Exception("product selection cannot happen in dispense product state");
    }

    @Override
    public int getChange(int returnChangeMoney) throws Exception {
        throw new Exception("refund cannot be happen in dispense product state");
    }

    @Override
    public Item dispenseProduct(VendingMachineService vendingMachineService, int productCode) throws Exception {
        Item item = vendingMachineService.getInventory().getItem(productCode);
        vendingMachineService.getInventory().removeItem(productCode);
        vendingMachineService.setVendingMachineState(new IdleState());
        System.out.println("Dispensing product: "+ item.toString());
        return item;
    }

    @Override
    public List<Coin> refundFullMoney(VendingMachineService vendingMachineService) throws Exception {
        throw new Exception("refund cannot be initiated in dispense product state");
    }

    @Override
    public void updateInventory(VendingMachineService vendingMachineService, Item item, int codeNumber) throws Exception {

    }
}
