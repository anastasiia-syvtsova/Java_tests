package de.uniba.wiai.dsg.ajp.assignment3;

public enum PriceCode {
    CHILDREN(2),
    REGULAR(0),
    NEW_RELEASE(1),
    LOWBUDGET(3);
    private int value;

    public int getValue() {
        return value;
    }

    PriceCode(int value) {
        this.value = value;
    }
}