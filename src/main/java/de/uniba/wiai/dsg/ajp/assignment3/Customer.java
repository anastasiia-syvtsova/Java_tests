package de.uniba.wiai.dsg.ajp.assignment3;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;

/**
 * <h1>Customer</h1>
 * Gives a representation of a Customer with their name and a list of Rentals that were rented.
 *
 * @version 1.0
 * @authors Benedikt (Markus) Marsiske, Jeremy Schierling, Dzmitry Novikau,
 * Anastasiia Syvtsova
 * @since 22.06.2021
 */

public class Customer {

    private String name;
    private List<Rental> rentals = new LinkedList<>();

    public Customer() {
    }

    /**
     * Creates a new Customer.
     *
     * @param name name of the new Customer
     * @throws IllegalArgumentException if name argument is null, empty or contains blank spaces only
     */
    public Customer(String name) {
        super();
        setName(name);
    }

    public String getName() {
        return name;
    }


    /**
     * Sets the name of the Customer.
     *
     * @param name name of the new Customer to set.
     * @throws IllegalArgumentException if argument is null or empty or contains blank spaces only
     */
    public void setName(String name) {
        this.name = InputValidator.validateString(name);
    }

    public List<Rental> getRentals() {
        return rentals;
    }

    /**
     * Sets a List of Rentals for a Customer
     *
     * @param rentals List of Rentals
     *                //	 * @throws IllegalArgumentException if argument is null
     */
    public void setRentals(List<Rental> rentals) {
        InputValidator.validateObject(rentals);
        this.rentals = rentals;
    }

    /**
     * Summarizes the Rental Record of a customer to a single String. The summary includes each movie separately with its title and rental charge.
     * The summary concludes with the total amount of rental charges owed and the collected frequent renter points of the customer.
     *
     * <p>
     * Precondition
     *     <ul>
     *         <li>The value of the customer's attributes must not be null or include objects that are null.</li>
     *     </ul>
     * </p>
     * <p>
     *     Postcondition
     *     <ul>
     *         <li>The value of the customer's attributes won't be changed by this function.</li>
     *     </ul>
     * </p>
     *
     * @return String statement of the customer's rental summary.
     * @see #htmlStatement()
     */
    public String statement() {
        StringBuilder result = new StringBuilder("Rental Record for " + getName() + "\n");
        DecimalFormat df = new DecimalFormat("0.00");
        int frequentRenterPoints = 0;
        for (Rental each : this.rentals) {
            frequentRenterPoints += each.getFrequentRenterPoints();

            // show figures for this rental
            result.append("\t").append(each.getMovie().getTitle()).append(" (").append(each.getMovie().getQuality().toString()).append(")\t").append(df.format(each.getCharge())).append("(Discount: ").append(each.getDiscount()).append("%)").append("\n");
        }

        // add footer lines
        result.append("Amount owed is ").append(df.format(getTotalCharge())).append("\n");
        result.append("You earned ").append(frequentRenterPoints).append(" frequent renter points");
        return result.toString();
    }

    /**
     * Summarizes the Rental Record of a customer to a HTML formatted String. The summary includes each movie separately with its title and rental charge.
     * The summary concludes with the total amount of rental charges owed and the collected frequent renter points of the customer.
     *
     * <p>
     * Precondition
     *     <ul>
     *         <li>The value of the customer's attributes must not be null or include objects that are null.</li>
     *     </ul>
     * </p>
     * <p>
     *     Postcondition
     *     <ul>
     *         <li>The value of the customer's attributes won't be changed by this function.</li>
     *     </ul>
     * </p>
     *
     * @return HTML formatted String statement of the customer's rental summary.
     * @see #statement()
     */
    public String htmlStatement() {
        StringBuilder result = new StringBuilder("<H1>Rentals for <EM>" + getName() + "</EM></H1><P>\n");

        DecimalFormat df = new DecimalFormat("0.00");
        for (Rental each : rentals) {
            // show figures for each rental
            result.append(each.getMovie().getTitle()).append("(").append(each.getMovie().getQuality().toString()).append("): ").append(df.format(each.getCharge())).append("(Discount: ").append(each.getDiscount()).append("%)").append("<BR>\n");
        }

        // add footer lines
        result.append("<P>You owe <EM>").append(df.format(getTotalCharge())).append("</EM><P>\n");
        result.append("On this rental you earned <EM>").append(getTotalFrequentRenterPoints()).append("</EM> frequent renter points<P>");
        return result.toString();
    }

    /**
     * Adds up the individual charges for each rented movie and returns the total charge for the customer.
     *
     * <p>
     * Precondition
     *     <ul>
     *         <li> Rentals must not be null or include objects that are null.</li>
     *     </ul>
     * </p>
     * <p>
     *     Postcondition
     *     <ul>
     *         <li>The value of the customer's attributes won't be changed by this function.</li>
     *     </ul>
     * </p>
     *
     * @return a double with the total charge of the customer
     */

    public double getTotalCharge() {
        double result = 0;

        for (Rental each : rentals) {
            result += each.getCharge();
        }

        return result;
    }

    /**
     * Adds up the individual frequent renter points for each rented movie and returns the total number of
     * collected points for the customer.
     *
     * <p>
     * Precondition
     *     <ul>
     *         <li> Rentals must not be null or include objects that are null.</li>
     *     </ul>
     * </p>
     * <p>
     *     Postcondition
     *     <ul>
     *         <li>The value of the customer's attributes won't be changed by this function.</li>
     *     </ul>
     * </p>
     *
     * @return integer with the total number of the customer's collected frequent renter points
     */
    public int getTotalFrequentRenterPoints() {
        int result = 0;

        for (Rental each : rentals) {
            result += each.getFrequentRenterPoints();
        }

        return result;
    }

}
