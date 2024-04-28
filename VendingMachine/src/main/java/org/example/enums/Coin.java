package org.example.enums;

public enum Coin {
    PENNY(1),
    NICKEL(5),
    DIME(10),
    QUARTER(25);

    @Override
    public String toString() {
        return "Coin{" +
                "value=" + value +
                '}';
    }

    public int value;
    Coin(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
