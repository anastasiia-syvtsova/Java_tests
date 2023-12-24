import de.uniba.wiai.dsg.ajp.assignment3.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StatementsTest {
    Customer customer = new Customer("Dieter Neumann");
    Movie childrenMovie4K = new Movie("Movie One", PriceCode.CHILDREN, MovieQuality.FOUR_K);
    Movie newReleaseMovieHD = new Movie("Movie Two", PriceCode.NEW_RELEASE, MovieQuality.HD);
    Movie regularMovieHD = new Movie("Movie Three", PriceCode.REGULAR, MovieQuality.HD);

    @Test
    public void correctInputShouldProduceACorrectStatement() throws IOException {
        //given
        Rental rentalOne = new Rental(5, childrenMovie4K);
        rentalOne.setDiscount(10);
        Rental rentalTwo = new Rental(1, newReleaseMovieHD);
        rentalTwo.setDiscount(20);
        Rental rentalThree = new Rental(1, regularMovieHD);
        rentalThree.setDiscount(30);
        List<Rental> rentals = new LinkedList<>();
        rentals.add(rentalOne);
        rentals.add(rentalTwo);
        rentals.add(rentalThree);
        customer.setRentals(rentals);
        String expectedStatement = readFile("Statement.txt");

        //when
        String resultStatement = customer.statement();

        //then
        assertEquals(expectedStatement, resultStatement);
    }


    @Test
    public void correctInputShouldProduceACorrectHTMLStatement() throws IOException {
        //given
        Rental rentalOne = new Rental(5, childrenMovie4K);
        rentalOne.setDiscount(10);
        Rental rentalTwo = new Rental(1, newReleaseMovieHD);
        rentalTwo.setDiscount(20);
        Rental rentalThree = new Rental(1, regularMovieHD);
        rentalThree.setDiscount(30);
        List<Rental> rentals = new LinkedList<>();
        rentals.add(rentalOne);
        rentals.add(rentalTwo);
        rentals.add(rentalThree);
        customer.setRentals(rentals);
        String expectedStatement = readFile("HTMLStatement.txt");

        //when
        String resultStatement = customer.htmlStatement();

        //then
        assertEquals(expectedStatement, resultStatement);
    }


    private String readFile(String filename) throws IOException {
        String inputFileAsString = "";
        String pathToInputFileString = "src/integrationtest/java/"  + filename;
        Path pathToInputFile = Path.of(pathToInputFileString);
        List<String> lines = Files.readAllLines(pathToInputFile, StandardCharsets.UTF_8);

        for (int i = 0; i < lines.size() - 1; i++) {
            inputFileAsString += lines.get(i) + "\n";
        }
        inputFileAsString += lines.get(lines.size() - 1);

        return inputFileAsString;
    }

}
