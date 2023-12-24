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
import static org.mockito.Mockito.mock;

public class MovieTests {

    @Test
    public void movieConstructorThrowsExceptionIfQualityIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            @SuppressWarnings("Cannot be used")
            Movie movie = new Movie("Movie", PriceCode.CHILDREN, null);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = { "", " ", "		" })
    public void movieConstructorThrowsExceptionIfTitleIsBlank(String title) {
        assertThrows(IllegalArgumentException.class, () -> {
            @SuppressWarnings("Cannot be used")
            Movie movie = new Movie(title, PriceCode.CHILDREN, MovieQuality.HD);
        });
    }

    @Test
    public void movieConstructorThrowsExceptionIfTitleIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            @SuppressWarnings("Cannot be used")
            Movie movie = new Movie(null, PriceCode.CHILDREN, MovieQuality.HD);
        });
    }

    @Test
    void setTitleThrowsExceptionIfInputIsNull() {
        Movie movie = new Movie("Movie", PriceCode.CHILDREN, MovieQuality.HD);
        assertThrows(IllegalArgumentException.class, () -> {
            movie.setTitle(null);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = { "", " ", "	" })
    public void setTitleThrowsExceptionIfInputIsBlank(String title) {
        Movie movie = new Movie("Movie", PriceCode.CHILDREN, MovieQuality.HD);
        assertThrows(IllegalArgumentException.class, () -> {
            movie.setTitle(title);
        });
    }

    @Test
    void setPriceCodeThrowsExceptionIfInputNull() {
        Movie movie = new Movie("Movie", PriceCode.CHILDREN, MovieQuality.HD);
        assertThrows(IllegalArgumentException.class, () -> {
            movie.setPriceCode(null);
        });
    }

    @ParameterizedTest
    @MethodSource("validPriceCodeInputs")
    void setPriceCodeWorksForValidInputs(PriceCode input, PriceCode output) {
        Movie movie = new Movie("Movie", PriceCode.CHILDREN, MovieQuality.HD);
        movie.setPriceCode(input);
        assertEquals(movie.getPriceCode(), output, "The wrong PriceCode is returned.");
    }

    public static List<Arguments> validPriceCodeInputs() {
        return List.of(Arguments.of(PriceCode.CHILDREN, PriceCode.CHILDREN),
                Arguments.of(PriceCode.REGULAR, PriceCode.REGULAR),
                Arguments.of(PriceCode.NEW_RELEASE, PriceCode.NEW_RELEASE),
                Arguments.of(PriceCode.LOWBUDGET, PriceCode.LOWBUDGET));
    }

    @ParameterizedTest
    @ValueSource(ints = { 0, -2, -350 })
    void getChargeShouldThrowExceptionForInputIsLessThan1(int input) {
        Movie movie = new Movie("Movie", PriceCode.CHILDREN, MovieQuality.HD);
        assertThrows(IllegalArgumentException.class, () -> {
            movie.getCharge(input);
        });
    }


    @Test
    void getFrequentRenterPointsIfInputIsLessThanOne() {
        Movie movie = new Movie("Movie", PriceCode.CHILDREN, MovieQuality.HD);
        assertThrows(IllegalArgumentException.class, () -> {
            movie.getFrequentRenterPoints(0);
        });
    }

    @Test
    public void movieConstructorThrowsExceptionIfPriceCodeIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            @SuppressWarnings("Cannot be used")
            Movie movie = new Movie("Movie", null, MovieQuality.HD);
        });
    }

    @ParameterizedTest
    @MethodSource("getChargeCorrectHDInputs")
    public void getChargeReturnsChargeOfHDMovie(int input, double result) {
        Movie movie = new Movie("Movie", PriceCode.CHILDREN, MovieQuality.HD);
        assertEquals(result, movie.getCharge(input));
    }

    public static List<Arguments> getChargeCorrectHDInputs() {
        return List.of(Arguments.of(1, 1.5), Arguments.of(2, 1.5));
    }

    @ParameterizedTest
    @MethodSource("getChargeCorrect4KInputs")
    public void getChargeShouldReturnChargeOf4KMovie(int input, double result, MovieQuality testQuality) {
        Movie movie = new Movie("Movie", PriceCode.CHILDREN, MovieQuality.HD);
        movie.setQuality(testQuality);
        assertEquals(result, movie.getCharge(input), "A movie with Quality 4K must cost 2 more");
    }

    public static List<Arguments> getChargeCorrect4KInputs() {
        return List.of(Arguments.of(1, 3.5, MovieQuality.FOUR_K), Arguments.of(2, 3.5, MovieQuality.FOUR_K));
    }
}
