package org.example.vendingstates;

import java.util.List;

import org.example.enums.Coin;
import org.example.enums.Item;
import org.example.service.VendingMachineService;

public interface State {
    void chickOnInsertCoinButton(VendingMachineService vendingMachineService) throws Exception;

    void chickOnProductSelectionButton(VendingMachineService vendingMachineService) throws Exception;

    void insertCoin(VendingMachineService vendingMachineService, Coin coins) throws Exception;

    void chooseProduct(VendingMachineService vendingMachineService, int productCode) throws Exception;

    int getChange(int returnChangeMoney) throws Exception;

    Item dispenseProduct(VendingMachineService vendingMachineService, int productCode) throws Exception;

    List<Coin> refundFullMoney(VendingMachineService vendingMachineService) throws Exception;

    void updateInventory(VendingMachineService vendingMachineService, Item item, int codeNumber) throws Exception;

}
