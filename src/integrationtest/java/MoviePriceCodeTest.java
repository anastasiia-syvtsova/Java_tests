import de.uniba.wiai.dsg.ajp.assignment3.Movie;
import de.uniba.wiai.dsg.ajp.assignment3.MovieQuality;
import de.uniba.wiai.dsg.ajp.assignment3.PriceCode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoviePriceCodeTest {

    @Test
    public void childrensPriceCodeShouldSetCorrectPriceCodeForMovie() {
        //given
        //when
        Movie movieOne = new Movie("Movie One", PriceCode.CHILDREN, MovieQuality.FOUR_K);
        //then
        assertEquals(PriceCode.CHILDREN, movieOne.getPriceCode());
    }

    @Test
    public void regularPriceCodeShouldSetCorrectPriceCodeForMovie() {
        //given
        //when
        Movie movieOne = new Movie("Movie One", PriceCode.REGULAR, MovieQuality.FOUR_K);
        //then
        assertEquals(PriceCode.REGULAR, movieOne.getPriceCode());
    }

    @Test
    public void newReleasePriceCodeShouldSetCorrectPriceCodeForMovie() {
        //given
        //when
        Movie movieOne = new Movie("Movie One", PriceCode.NEW_RELEASE, MovieQuality.FOUR_K);
        //then
        assertEquals(PriceCode.NEW_RELEASE, movieOne.getPriceCode());
    }
}
