package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

import org.example.enums.Coin;
import org.example.enums.Item;
import org.example.model.Inventory;
import org.example.model.ItemShelf;
import org.example.service.VendingMachineService;
import org.example.vendingstates.State;

public class Main {
    public static void main(String[] args) {
        VendingMachineService vendingMachineService = new VendingMachineService();

        try {
            filltheInventory(vendingMachineService);
            displayTheInventory(vendingMachineService);

            System.out.println("| clicking on InsertCoinButton |");
            System.out.println();

            State vendingState = vendingMachineService.getVendingMachineState();
            vendingState.chickOnInsertCoinButton(vendingMachineService);

            vendingState = vendingMachineService.getVendingMachineState();
            vendingState.insertCoin(vendingMachineService, Coin.QUARTER);
            vendingState.insertCoin(vendingMachineService, Coin.QUARTER);
            vendingState.insertCoin(vendingMachineService, Coin.QUARTER);

            System.out.println();
            System.out.println("| clicking on product selection button |");
            vendingState.chickOnProductSelectionButton(vendingMachineService);

            vendingState = vendingMachineService.getVendingMachineState();
            vendingState.chooseProduct(vendingMachineService, 101);

            displayTheInventory(vendingMachineService);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static void displayTheInventory(VendingMachineService vendingMachineService) {
        Map<Integer, ItemShelf> inventory = vendingMachineService.getInventory().getInnvetory();
        System.out.println();
        System.out.println("----------------Items Available in Inventory-----------------");
        for(Map.Entry<Integer, ItemShelf> el : inventory.entrySet()) {
            System.out.println("ShelfCode: " + el.getKey() + " -> "+el.getValue());
        }
        System.out.println("-------------------------------------------------------------");
        System.out.println();
    }

    private static void filltheInventory(VendingMachineService vendingMachineService) {
        Map<Integer, ItemShelf> integerItemShelfMap =vendingMachineService.getInventory().getInnvetory();
        ItemShelf itemShelf = new ItemShelf(101, new Item(ItemType.PEPSI, 50), 2, false);
        integerItemShelfMap.put(itemShelf.getCode(), itemShelf);

        ItemShelf itemShelf1 = new ItemShelf(102, new Item(ItemType.JUICE, 40), 1,false);
        integerItemShelfMap.put(itemShelf1.getCode(), itemShelf1);

        ItemShelf itemShelf2 = new ItemShelf(103, new Item(ItemType.SODA, 20), 20,false);
        integerItemShelfMap.put(itemShelf2.getCode(), itemShelf2);
    }
}
