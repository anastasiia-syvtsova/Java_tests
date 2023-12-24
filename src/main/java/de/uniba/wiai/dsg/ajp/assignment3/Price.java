package de.uniba.wiai.dsg.ajp.assignment3;

public abstract class Price {

	abstract double getCharge(int daysRented);

	int getFrequentRenterPoints(int daysRented) {
		InputValidator.validateInteger(daysRented);
		return 1;
	}

	abstract PriceCode getPriceCode();

}
