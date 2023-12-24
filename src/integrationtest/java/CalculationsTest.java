import de.uniba.wiai.dsg.ajp.assignment3.*;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculationsTest {
    Customer customer = new Customer("Customer");


    @Test
    public void totalChargeShouldAddUpCorrectlyForRegularCustomerAndNewReleaseMovies() {
        //given
        Movie newReleaseHDMovie = new Movie("Bla-bla", PriceCode.NEW_RELEASE, MovieQuality.HD);
        Movie newRelease4KMovie = new Movie("Bla-bla", PriceCode.NEW_RELEASE, MovieQuality.FOUR_K);
        Rental rentalHD = new Rental(2, newReleaseHDMovie);
        Rental rental4K = new Rental(2, newRelease4KMovie);
        List<Rental> rentals = new LinkedList<>();
        rentals.add(rentalHD);
        rentals.add(rental4K);
        customer.setRentals(rentals);

        //when
        double calculatedCharge = customer.getTotalCharge();

        //then
        assertEquals(14.0, calculatedCharge);
    }

    @Test
    public void totalChargeShouldAddUpCorrectlyForRegularCustomerAndChildrenMovies() {
        //given
        Movie childrensHDMovie = new Movie("Bla-bla", PriceCode.CHILDREN, MovieQuality.HD);
        Movie childrens4KMovie = new Movie("Bla-bla", PriceCode.CHILDREN, MovieQuality.FOUR_K);
        Rental rentalHD1Day = new Rental(1, childrensHDMovie);
        Rental rental4K2Days = new Rental(2, childrens4KMovie);
        Rental rentalHD3Days = new Rental(3, childrensHDMovie);
        Rental rental4K4Days = new Rental(4, childrens4KMovie);
        List<Rental> rentals = new LinkedList<>();
        rentals.add(rentalHD1Day);
        rentals.add(rental4K2Days);
        rentals.add(rentalHD3Days);
        rentals.add(rental4K4Days);
        customer.setRentals(rentals);

        //when
        double calculatedCharge = customer.getTotalCharge();

        //then
        assertEquals(11.5, calculatedCharge);
    }

    @Test
    public void totalChargeShouldAddUpCorrectlyForRegularCustomerAndRegularMovies() {
        //given
        Movie regularHDMovie = new Movie("Bla-bla", PriceCode.REGULAR, MovieQuality.HD);
        Movie regular4KMovie = new Movie("Bla-bla", PriceCode.REGULAR, MovieQuality.FOUR_K);
        Rental rentalHD1Day = new Rental(1, regularHDMovie);
        Rental rental4K2Days = new Rental(2, regular4KMovie);
        Rental rentalHD3Days = new Rental(3, regularHDMovie);
        Rental rental4K4Days = new Rental(4, regular4KMovie);
        List<Rental> rentals = new LinkedList<>();
        rentals.add(rentalHD1Day);
        rentals.add(rental4K2Days);
        rentals.add(rentalHD3Days);
        rentals.add(rental4K4Days);
        customer.setRentals(rentals);

        //when
        double calculatedCharge = customer.getTotalCharge();

        //then
        assertEquals(16.5, calculatedCharge);
    }

    @Test
    public void totalChargeForRegularCustomerWithoutRentalsShouldAddUpToZero() {
        //given
        customer.setRentals(new LinkedList<>());
        //when
        double calculatedCharge = customer.getTotalCharge();
        //then
        assertEquals(0, calculatedCharge);
    }

    @Test
    public void totalFRPointsShouldAddUpCorrectlyForChildrensAndRegularMovies() {
        //given
        Movie regularHDMovie = new Movie("Bla-bla", PriceCode.REGULAR, MovieQuality.HD);
        Movie children4KMovie = new Movie("Bla-bla", PriceCode.CHILDREN, MovieQuality.FOUR_K);
        Rental rentalHD = new Rental(1, regularHDMovie);
        Rental rental4K = new Rental(2, children4KMovie);
        List<Rental> rentals = new LinkedList<>();
        rentals.add(rentalHD);
        rentals.add(rental4K);
        customer.setRentals(rentals);

        //when
        int calculatedFRPoints = customer.getTotalFrequentRenterPoints();

        //then
        assertEquals(2, calculatedFRPoints);
    }

    @Test
    public void totalFRPointsShouldAddUpCorrectlyForNewReleaseMovies() {
        //given
        Movie newReleaseHDMovie = new Movie("Bla-bla", PriceCode.NEW_RELEASE, MovieQuality.HD);
        Movie newRelease4KMovie = new Movie("Bla-bla", PriceCode.NEW_RELEASE, MovieQuality.FOUR_K);
        Rental rentalHD = new Rental(1, newReleaseHDMovie);
        Rental rental4K = new Rental(2, newRelease4KMovie);
        List<Rental> rentals = new LinkedList<>();
        rentals.add(rentalHD);
        rentals.add(rental4K);
        customer.setRentals(rentals);

        //when
        int calculatedFRPoints = customer.getTotalFrequentRenterPoints();

        //then
        assertEquals(3, calculatedFRPoints);
    }

}
