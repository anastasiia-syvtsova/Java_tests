package de.uniba.wiai.dsg.ajp.assignment3;

public class ChildrenPrice extends Price {

    @Override
    double getCharge(int daysRented) {
        InputValidator.validateInteger(daysRented);
        double result = 1.5;
        if (daysRented > 3) {
            result += (daysRented - 3) * 1.5;
        }
        return result;
    }

    @Override
    PriceCode getPriceCode() {
        return PriceCode.CHILDREN;
    }

}
