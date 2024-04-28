package org.example.model;

import lombok.Getter;
import lombok.Setter;
import org.example.enums.Item;

public class ItemShelf {

    public ItemShelf(int code, Item item, int quantity, boolean soldOut) {
        this.code = code;
        this.item = item;
        this.soldOut = soldOut;
        this.quantity = quantity;
    }

    int code;
    Item item;

    @Setter
    @Getter
    int quantity;
    boolean soldOut;

    public int getCode() { return code; }

    public void setCode(int code) { this.code = code; }

    public Item getItem() {
        return this.item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public boolean isSoldOut() {
        return soldOut;
    }

    public void setSoldOut(boolean soldOut) {
        this.soldOut = soldOut;
    }

    @Override
    public String toString() {
        return "ItemShelf{" +
                "code=" + code +
                ", item=" + item +
                ", quantity=" + quantity +
                ", soldOut=" + soldOut +
                '}';
    }
}
