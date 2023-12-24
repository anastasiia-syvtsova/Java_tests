package de.uniba.wiai.dsg.ajp.assignment3;

/**
 * <h1>Movie</h1>
 * Gives a representation of a Movie with title, price and quality.
 *
 * @version 1.0
 * @authors Benedikt (Markus) Marsiske, Jeremy Schierling, Dzmitry Novikau,
 * Anastasiia Syvtsova
 * @since 22.06.2021
 */


public class Movie {

    private Price price;
    private String title;
    private MovieQuality quality;

    /**
     * Creates a new Movie.
     *
     * @param title     Title of the new Movie. Must not be null or blank.
     * @param priceCode PriceCode Enum value represents the type of the Movie. Must not be null.
     * @param quality   Quality Enum value represents the quality of the Movie. Must not be null.
     * @throws IllegalArgumentException in case if any of the arguments is invalid
     */
    public Movie(String title, PriceCode priceCode, MovieQuality quality) {
        setTitle(title);
        setPriceCode(priceCode);
        setQuality(quality);
    }

    /**
     * Returns the title of the movie
     */

    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the Movie
     *
     * @param title title of the Movie. Must not be null or blank.
     * @throws IllegalArgumentException if title is null or blank
     */
    public void setTitle(String title) {
        this.title = InputValidator.validateString(title);
    }

    /**
     * Returns the movie quality of the movie
     */
    public MovieQuality getQuality() {
        return quality;
    }

    /**
     * Sets the quality of the movie
     *
     * @param quality Quality Enum value represents the quality of the Movie. Must not be null.
     * @throws IllegalArgumentException in the case if quality is null
     */
    public void setQuality(MovieQuality quality) {
        InputValidator.validateObject(quality);
        this.quality = quality;
    }

    /**
     * Summarizes the charge for a Movie rent according to the number of days rented by taking
     * the Movie's price. If the Movie is of 4K quality, an extra charge of 2 will be added.
     *
     * @param daysRented number of days that the Movie was rented. Must not be less than 1
     * @return charge for the Movie and the number of days
     * @throws IllegalArgumentException if daysRented is less than 1
     */
    double getCharge(int daysRented) {
        double charge = price.getCharge(InputValidator.validateInteger(daysRented));
        if (quality == MovieQuality.FOUR_K) {
            charge += 2;
        }
        return charge;
    }

    /**
     * Returns a PriceCode Enum value, which represents the price of the Movie
     *
     * @return returns the PriceCode of the Movie
     */
    public PriceCode getPriceCode() {
        return price.getPriceCode();
    }

    /**
     * Sets the priceCode for a movie which determines its price structure and thus the amount of the rental charges per rented day.
     * There are three different price structures available:
     *
     * <p>
     *     <ul>
     *         <li><b>regular: </b>the normal price structure for movie rental</li>
     *         <li><b>children's: </b>the cheaper price structure for children</li>
     *         <li><b>new releases: </b>the more expensive price structure for new releases</li>
     *     </ul>
     * </p>
     *
     * @param priceCode constant integer which represents one of the available price structures
     */
    public void setPriceCode(PriceCode priceCode) {
        InputValidator.validateObject(priceCode);
        switch (priceCode) {
            case REGULAR:
                price = new RegularPrice();
                break;
            case CHILDREN:
                price = new ChildrenPrice();
                break;
            case NEW_RELEASE:
                price = new NewReleasePrice();
                break;
            case LOWBUDGET:
                price = new LowBudgetPrice();
                break;
            default:
                throw new IllegalArgumentException("Incorrect Price Code");
        }
    }

    /**
     * Returns the number of frequent renter points that was earned by renting the movie
     * during the specified number of days.
     *
     * @param daysRented number of days rented. Must not be less than 1
     * @return number of frequent renter points
     * @throws IllegalArgumentException if daysRented is less than 1
     */
    public int getFrequentRenterPoints(int daysRented) {
        return price.getFrequentRenterPoints(daysRented);
    }
}
