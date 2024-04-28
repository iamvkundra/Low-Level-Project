package org.example.model;

import java.util.HashMap;
import java.util.Map;

import org.example.enums.Item;

public class Inventory {
    Map<Integer, ItemShelf> itemShelfMap = new HashMap<>();

    public Inventory() {
        this.itemShelfMap = new HashMap<>();
    }

    public Map<Integer, ItemShelf> getInnvetory() {
        return this.itemShelfMap;
    }

    public void setInventory(Map<Integer, ItemShelf> inventory) {
        this.itemShelfMap = inventory;
    }

    public void addItem(Item item, int quantity, int itemCode) {
        itemShelfMap.put(itemCode, new ItemShelf(itemCode, item, quantity,false));
    }

    public Item getItem(int productCode) throws Exception {
        if(itemShelfMap.containsKey(productCode)) {
            ItemShelf itemShelf = itemShelfMap.get(productCode);
            if (itemShelf.getQuantity() >= 1) {
                return itemShelf.getItem();
            } else {
                return null;
            }
        }
        return null;
    }

    public void removeItem(int productCode) throws Exception {
        if (itemShelfMap.containsKey(productCode)) {
            ItemShelf itemShelf = itemShelfMap.get(productCode);
            if (itemShelf.getQuantity() > 0) {
                itemShelf.setQuantity(itemShelf.getQuantity()-1);
                if (itemShelf.getQuantity() == 0) {
                    itemShelf.setSoldOut(true);
                }
            }else {
                throw new Exception("Item is unavailable: productCode: "+productCode);
            }
            itemShelfMap.put(productCode, itemShelf);
        }
    }
}
