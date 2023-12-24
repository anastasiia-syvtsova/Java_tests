package de.uniba.wiai.dsg.ajp.assignment3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ChildrenPriceTests {

    private ChildrenPrice childrenPrice;

    @BeforeEach
    public void setUp() {
        childrenPrice = new ChildrenPrice();
    }

    @ParameterizedTest
    @MethodSource("correctInputsForGetChargeMethod")
    public void getChargeReturnsCorrectChargeForAllRentDurations(int input, double output) {
        assertEquals(output, childrenPrice.getCharge(input), "The wrong charge is returned.");
    }

    public static List<Arguments> correctInputsForGetChargeMethod() {
        return List.of(Arguments.of(1, 1.5), Arguments.of(3, 1.5), Arguments.of(4, 3), Arguments.of(99, 145.5));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, -99})
    public void getChargeThrowsIllegalArgumentExceptionForInvalidParameters(int input) {
        assertThrows(IllegalArgumentException.class, () -> {
            childrenPrice.getCharge(input);
        });
    }


    @ParameterizedTest
    @ValueSource(ints = {1, 5, 99})
    public void getFrequentRenterPointsAlwaysReturnsOne(int input) {
        assertEquals(1, childrenPrice.getFrequentRenterPoints(input));

    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, -99})
    public void getFrequentRenterPointsThrowsIllegalArgumentExceptionForInvalidParameters(int input) {
        assertThrows(IllegalArgumentException.class, () -> {
            childrenPrice.getFrequentRenterPoints(input);
        });
    }
}
