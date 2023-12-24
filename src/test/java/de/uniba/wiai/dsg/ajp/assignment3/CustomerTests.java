package de.uniba.wiai.dsg.ajp.assignment3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class CustomerTests {

    private Customer customer;

    @BeforeEach
    public void setUp() {
        customer = new Customer();
    }

    @Test
    public void customerNameCanBeSetWithConstructor() {
        Customer customer1 = new Customer("Martin Fowler");
        assertEquals("Martin Fowler", customer1.getName(), "Name should be Martin Fowler");
    }

    @Test
    public void customerNameCanBeSetWithSetter() {
        customer.setName("Martin Fowler");
        assertEquals("Martin Fowler", customer.getName(), "Name should be Martin Fowler");
    }


    @ParameterizedTest
    @ValueSource(strings = {"", " ", "	"})
    public void customerConstructorThrowsExceptionIfNameIsBlankString(String name) {
        assertThrows(IllegalArgumentException.class, () -> {
            @SuppressWarnings("Can not be used")
            Customer customer = new Customer(name);
        });
    }

    @Test
    public void customerNameIsEmptyThrowsExceptionWithConstructor() {
        assertThrows(
                IllegalArgumentException.class, () -> {
                    @SuppressWarnings("The validation should not allow the input to be empty.")
                    Customer customer = new Customer("");
                });
    }


    @Test
    public void customerNameIsNullThrowsExceptionWithConstructor() {
        assertThrows(
                IllegalArgumentException.class, () -> {
                    @SuppressWarnings("The validation should not allow the input to be null.")
                    Customer customer = new Customer(null);
                });
    }

    @ParameterizedTest
    @ValueSource(strings = {"James", "Linda", "Robert", "Sara", "Cathrine"})
    public void getNameShouldReturnInstanceVariableNameAsString(String input) {
        Customer customerTest = new Customer(input);
        assertEquals(input, customerTest.getName());

    }

    @Test
    public void customerNameIsNullThrowsExceptionWithSetter() {
        assertThrows(
                IllegalArgumentException.class,
                () -> customer.setName(null),
                "The validation should not allow the input to be null."
        );
    }

    @Test
    public void customerNameIsEmptyThrowsExceptionWithSetter() {
        assertThrows(
                IllegalArgumentException.class,
                () -> customer.setName(""),
                "The validation should not allow the input to be empty."
        );
    }

    @Test
    public void setRentalsWorksAsIntended() {
        Rental rental1 = mock(Rental.class);
        Rental rental2 = mock(Rental.class);
        Rental rental3 = mock(Rental.class);
        List<Rental> rentalList = new LinkedList<>();
        rentalList.add(rental1);
        rentalList.add(rental2);
        rentalList.add(rental3);
        customer.setRentals(rentalList);

        assertEquals(customer.getRentals().size(), 3, "The Setter of rentals was not successful.");
    }

    @Test
    public void setRentalsToNullThrowsException() {
        List<Rental> rentalList = null;
        assertThrows(
                IllegalArgumentException.class,
                () -> customer.setRentals(rentalList),
                "The rentals should not be able to be set when the provided rental list is null."
        );
    }

    @Test
    public void statementShouldReturnReceiptAsString() throws IOException {
        // given
        Customer customerTest = new Customer("Dieter Neumann");
        Rental rentalMock = mock(Rental.class);
        Rental rentalMock2 = mock(Rental.class);
        Movie movieMock = mock(Movie.class);
        Movie movieMock2 = mock(Movie.class);

        List<Rental> rentalList = new LinkedList<>();
        rentalList.add(rentalMock);
        rentalList.add(rentalMock2);
        customerTest.setRentals(rentalList);

        when(rentalMock.getFrequentRenterPoints()).thenReturn(3);
        when(movieMock.getTitle()).thenReturn("Ten");
        when(rentalMock.getMovie()).thenReturn(movieMock);
        when(movieMock.getQuality()).thenReturn(MovieQuality.HD);
        when(rentalMock.getCharge()).thenReturn(7.5);
        when(rentalMock.getDiscount()).thenReturn(10);


        when(rentalMock2.getFrequentRenterPoints()).thenReturn(4);
        when(movieMock2.getTitle()).thenReturn("Eleven");
        when(movieMock2.getQuality()).thenReturn(MovieQuality.FOUR_K);
        when(rentalMock2.getMovie()).thenReturn(movieMock2);
        when(rentalMock2.getCharge()).thenReturn(7.5);
        when(rentalMock2.getDiscount()).thenReturn(20);

        String expectedStatement = readFile("customer.txt");

        // when
        String resultStatement = customerTest.statement();

        // then
        assertEquals(expectedStatement, resultStatement);
    }

    @Test
    public void htmlStatementShouldReturnReceiptAsString() throws IOException {
        // given
        Customer customerTest = new Customer("Dieter Neumann");
        Rental rentalMock = mock(Rental.class);
        Rental rentalMock2 = mock(Rental.class);
        Movie movieMock = mock(Movie.class);
        Movie movieMock2 = mock(Movie.class);

        List<Rental> rentalList = new LinkedList<>();
        rentalList.add(rentalMock);
        rentalList.add(rentalMock2);
        customerTest.setRentals(rentalList);

        when(movieMock.getTitle()).thenReturn("Ten");
        when(rentalMock.getMovie()).thenReturn(movieMock);
        when(movieMock.getQuality()).thenReturn(MovieQuality.HD);
        when(rentalMock.getCharge()).thenReturn(7.5);
        when(rentalMock.getDiscount()).thenReturn(10);

        when(movieMock2.getTitle()).thenReturn("Eleven");
        when(movieMock2.getQuality()).thenReturn(MovieQuality.FOUR_K);
        when(rentalMock2.getMovie()).thenReturn(movieMock2);
        when(rentalMock2.getCharge()).thenReturn(8.5);
        when(rentalMock2.getDiscount()).thenReturn(20);

        when(customerTest.getTotalFrequentRenterPoints()).thenReturn(7);

        String expectedStatement = readFile("customer_html.txt");

        // when
        String resultStatement = customerTest.htmlStatement();

        // then
        assertEquals(expectedStatement, resultStatement);
    }

    @Test
    public void getTotalChargeShouldReturnSumOfCharges() {

        Rental rentalMock1 = mock(Rental.class);
        Rental rentalMock2 = mock(Rental.class);
        Rental rentalMock3 = mock(Rental.class);
        List<Rental> rentalList = new LinkedList<>();
        rentalList.add(rentalMock1);
        rentalList.add(rentalMock2);
        rentalList.add(rentalMock3);

        Customer customerTest = new Customer("Lily");
        customerTest.setRentals(rentalList);

        when(rentalMock1.getCharge()).thenReturn(5.0);
        when(rentalMock2.getCharge()).thenReturn(4.0);
        when(rentalMock3.getCharge()).thenReturn(3.0);


        assertEquals(12, customerTest.getTotalCharge());
    }

    @Test
    public void getTotalFrequentRenterPointsShouldReturnSumOfRenterPoints() {

        Rental rentalMock1 = mock(Rental.class);
        Rental rentalMock2 = mock(Rental.class);
        Rental rentalMock3 = mock(Rental.class);
        List<Rental> rentalList = new LinkedList<>();
        rentalList.add(rentalMock1);
        rentalList.add(rentalMock2);
        rentalList.add(rentalMock3);

        Customer customerTest = new Customer("Jane");
        customerTest.setRentals(rentalList);

        when(rentalMock1.getFrequentRenterPoints()).thenReturn(7);
        when(rentalMock2.getFrequentRenterPoints()).thenReturn(3);
        when(rentalMock3.getFrequentRenterPoints()).thenReturn(3);


        assertEquals(13, customerTest.getTotalFrequentRenterPoints());
    }

    @Test
    private String readFile(String filename) throws IOException {
        String inputFileAsString = "";

        String pathToInputFileString = "src\\test\\java\\de\\uniba\\wiai\\dsg\\ajp\\assignment3\\" + filename;
        Path pathToInputFile = Path.of(pathToInputFileString);
        List<String> lines = Files.readAllLines(pathToInputFile, StandardCharsets.UTF_8);

        for (int i = 0; i < lines.size() - 1; i++) {
            inputFileAsString += lines.get(i) + "\n";
        }
        inputFileAsString += lines.get(lines.size() - 1);

        return inputFileAsString;
    }

}












