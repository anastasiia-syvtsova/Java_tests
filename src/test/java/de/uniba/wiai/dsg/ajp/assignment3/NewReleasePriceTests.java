package de.uniba.wiai.dsg.ajp.assignment3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NewReleasePriceTests {
    private NewReleasePrice newReleasePrice;

    @BeforeEach
    public void setUp() {
        newReleasePrice = new NewReleasePrice();
    }

    @ParameterizedTest
    @MethodSource("correctInputsForGetChargeMethod")
    public void getChargeReturnsCorrectChargeForAllRentDurations(int input, double output) {
        assertEquals(output, newReleasePrice.getCharge(input), "The wrong charge is returned.");
    }

    public static List<Arguments> correctInputsForGetChargeMethod() {
        return List.of(Arguments.of(1, 3), Arguments.of(2, 6), Arguments.of(3, 9), Arguments.of(99, 297));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, -99})
    public void getChargeThrowsIllegalArgumentExceptionForInvalidParameters(int input) {
        assertThrows(IllegalArgumentException.class, () -> {
            newReleasePrice.getCharge(input);
        });
    }

    @ParameterizedTest
    @ValueSource(ints = { 1, 0 })
    public void getFrequentRenterPointsReturnsOneIfDaysRentedLessThenTwo() {
        assertEquals(1, newReleasePrice.getFrequentRenterPoints(1), "The rented points should be more than 2");

    }


    @ParameterizedTest
    @MethodSource("correctInputsForGetFrequentRenterPointsMethod")
    public void getFrequentRenterPointsReturnsCorrectPointsForAllDurations(int input, int output) {
        assertEquals(output, newReleasePrice.getFrequentRenterPoints(input));
    }

    public static List<Arguments> correctInputsForGetFrequentRenterPointsMethod() {
        return List.of(Arguments.of(1, 1), Arguments.of(2, 2), Arguments.of(99, 2));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, -99})
    public void getFrequentRenterPointsThrowsIllegalArgumentExceptionForInvalidParameters(int input) {
        assertThrows(IllegalArgumentException.class, () -> {
            newReleasePrice.getFrequentRenterPoints(input);
        });
    }
}
