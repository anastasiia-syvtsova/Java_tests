package de.uniba.wiai.dsg.ajp.assignment3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LowBudgetPriceTests {
    private LowBudgetPrice lowBudgetPrice;

    @BeforeEach
    public void setUp() {
        lowBudgetPrice = new LowBudgetPrice();
    }

    @ParameterizedTest
    @MethodSource("correctInputsForGetChargeMethod")
    public void getChargeReturnsCorrectChargeForAllRentDurations(int input, double output) {
        assertEquals(output, lowBudgetPrice.getCharge(input), "The wrong charge is returned.");
    }

    public static List<Arguments> correctInputsForGetChargeMethod() {
        return List.of(Arguments.of(1, 0.5), Arguments.of(2, 2), Arguments.of(3, 1.5), Arguments.of(99, 49.5));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, -99})
    public void getChargeThrowsIllegalArgumentExceptionForInvalidParameters(int input) {
        assertThrows(IllegalArgumentException.class, () -> {
            lowBudgetPrice.getCharge(input);
        });
    }


    @ParameterizedTest
    @ValueSource(ints = {1, 5, 99})
    public void getFrequentRenterPointsAlwaysReturnsZero(int input) {
        assertEquals(0, lowBudgetPrice.getFrequentRenterPoints(input));

    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, -99})
    public void getFrequentRenterPointsThrowsIllegalArgumentExceptionForInvalidParameters(int input) {
        assertThrows(IllegalArgumentException.class, () -> {
            lowBudgetPrice.getFrequentRenterPoints(input);
        });
    }
}
