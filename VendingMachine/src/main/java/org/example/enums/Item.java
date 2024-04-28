package org.example.enums;

import org.example.ItemType;

public class Item {
    ItemType itemType;
    int price;

    @Override
    public String toString() {
        return "Item{" +
                "itemType=" + itemType +
                ", price=" + price +
                '}';
    }

    public Item(ItemType itemType, int price) {
        this.itemType = itemType;
        this.price = price;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
