package de.uniba.wiai.dsg.ajp.assignment3;

public class NewReleasePrice extends Price {

    @Override
    double getCharge(int daysRented) {
        InputValidator.validateInteger(daysRented);
        return daysRented * 3;
    }

    @Override
    int getFrequentRenterPoints(int daysRented) {
        InputValidator.validateInteger(daysRented);
        if (daysRented > 1) {
            return 2;
        } else {
            return 1;
        }
    }

    @Override
    PriceCode getPriceCode() {
        return PriceCode.NEW_RELEASE;
    }

}
