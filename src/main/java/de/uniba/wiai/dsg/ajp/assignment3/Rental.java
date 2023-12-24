package de.uniba.wiai.dsg.ajp.assignment3;

public class Rental {

    private int daysRented;
    private Movie movie;
    private int discount = 0;

    public Rental(int daysRented, Movie movie) {
        setDaysRented(daysRented);
        setMovie(movie);
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        InputValidator.validateObject(movie);
        this.movie = movie;
    }

    public int getDaysRented() {
        return daysRented;
    }

    public void setDaysRented(int daysRented) {
        this.daysRented = InputValidator.validateInteger(daysRented);
    }

    public double getCharge() {
        return movie.getCharge(daysRented) * (1 - this.getDiscount() / 100.0);
    }

    public int getFrequentRenterPoints() {
        return movie.getFrequentRenterPoints(daysRented);
    }

    public int getDiscount() {
        return this.discount;
    }

    public void setDiscount(int discount) {
        this.discount = InputValidator.validateDiscount(discount);
    }


}
