package de.uniba.wiai.dsg.ajp.assignment3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RegularPriceTests {
    private RegularPrice regularPrice;

    @BeforeEach
    public void setUp() {
        regularPrice = new RegularPrice();
    }

    @ParameterizedTest
    @MethodSource("correctInputsForGetChargeMethod")
    public void getChargeReturnsCorrectChargeForAllRentDurations(int input, double output) {
        assertEquals(output, regularPrice.getCharge(input), "The wrong charge is returned.");
    }

    public static List<Arguments> correctInputsForGetChargeMethod() {
        return List.of(Arguments.of(1, 2), Arguments.of(2, 2), Arguments.of(3, 3.5), Arguments.of(99, 147.5));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, -99})
    public void getChargeThrowsIllegalArgumentExceptionForInvalidParameters(int input) {
        assertThrows(IllegalArgumentException.class, () -> {
            regularPrice.getCharge(input);
        });
    }


    @ParameterizedTest
    @ValueSource(ints = {1, 5, 99})
    public void getFrequentRenterPointsAlwaysReturnsOne(int input) {
        assertEquals(1, regularPrice.getFrequentRenterPoints(input));

    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, -99})
    public void getFrequentRenterPointsThrowsIllegalArgumentExceptionForInvalidParameters(int input) {
        assertThrows(IllegalArgumentException.class, () -> {
            regularPrice.getFrequentRenterPoints(input);
        });
    }
}
