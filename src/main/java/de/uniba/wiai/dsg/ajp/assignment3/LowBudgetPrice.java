package de.uniba.wiai.dsg.ajp.assignment3;

public class LowBudgetPrice extends Price {

    @Override
    double getCharge(int daysRented) {
        InputValidator.validateInteger(daysRented);
        if (daysRented == 2) {
            return daysRented;
        }
        return daysRented * 0.5;
    }

    @Override
    PriceCode getPriceCode() {
        return PriceCode.LOWBUDGET;
    }

    @Override
    int getFrequentRenterPoints(int daysRented) {
        InputValidator.validateInteger(daysRented);
        return 0;
    }
}
