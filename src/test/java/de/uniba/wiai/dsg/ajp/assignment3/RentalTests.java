package de.uniba.wiai.dsg.ajp.assignment3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RentalTests {
    Movie movieMock;

    public static List<Arguments> validInputsForDiscountOptions() {
        return List.of(Arguments.of(0, 0), Arguments.of(1, 1), Arguments.of(50, 50), Arguments.of(100, 100));
    }

    public static List<Arguments> validInputsForDiscountOptionsForGetChargeMethod() {
        return List.of(Arguments.of(0, 200), Arguments.of(20, 160), Arguments.of(50, 100), Arguments.of(100, 0));
    }

    @BeforeEach
    public void setUp() {
        movieMock = mock(Movie.class);
    }

    @Test
    public void rentalConstructorShouldThrowExceptionForZeroDaysRented() {
        // given/when/then
        assertThrows(IllegalArgumentException.class, () -> {
            @SuppressWarnings("unused")
            Rental newRental = new Rental(0, movieMock);
        });
    }

    @Test
    public void rentalConstructorShouldThrowExceptionForMovieIsNull() {
        // given/when/then
        assertThrows(IllegalArgumentException.class, () -> {
            @SuppressWarnings("unused")
            Rental newRental = new Rental(1, null);
        });
    }

    @Test
    public void setMovieShouldThrowExceptionForInputNull() {
        // given
        Rental testRental = new Rental(3, movieMock);
        // when/then
        assertThrows(IllegalArgumentException.class, () -> testRental.setMovie(null));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, -243})
    public void setDaysRentedShouldThrowExceptionForInputLessThan1() {
        // given
        Rental testRental = new Rental(3, movieMock);
        // when/then
        assertThrows(IllegalArgumentException.class, () -> testRental.setDaysRented(0));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -243, 101, 1002})
    public void setDiscountShouldThrowExceptionForInvalidInputs(int ints) {
        // given
        Rental testRental = new Rental(3, movieMock);
        // when/then
        assertThrows(IllegalArgumentException.class, () -> testRental.setDiscount(ints));
    }

    @ParameterizedTest
    @MethodSource("validInputsForDiscountOptions")
    public void setDiscountWithValidInputsShouldNotThrowException(int input, int output) {
        // given
        Rental testRental = new Rental(3, movieMock);
        //when
        testRental.setDiscount(input);
        //then
        assertEquals(testRental.getDiscount(), output, "The wrong discount is returned.");
    }

    @ParameterizedTest
    @MethodSource("validInputsForDiscountOptionsForGetChargeMethod")
    public void getChargeReturnsCorrectChargeWithDiscount(int discount, double output) {
        //given
        when(movieMock.getCharge(anyInt())).thenReturn(200.0);
        Rental rentalWithMovieMock = new Rental(1, movieMock);
        //when
        rentalWithMovieMock.setDiscount(discount);
        //then
        assertEquals(output, rentalWithMovieMock.getCharge(), "The wrong charge is returned.");
    }

}


