package za.ac.cput.factory;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Mpumzi Mbula
 * 219053324
 * 19/05/2024
 */
class CartFactoryTest {
    private static Cart cart1;
    private static Cart cart2;
    private static Cart cart3;
    private static Customer customer1;
    private static Customer customer2;
    private static Customer customer3;

    static byte[] photo;
    static private Author author1;
    private static Author author2;

    private static List<Author> authors;
    private static ComicBook book1;
    private static ComicBook book2;
    private static ComicBook book3;
    private static Publisher publisher1;

    private static List<ComicBook> comicBooks;
    static Address billingAddress;
    static Address shippingAddress;



    @BeforeAll
    static void setUp() {


        billingAddress = BillingAddressFactory.buildBillingAddress("card", "34 Batersea Drive", "Kibbler park", "2091", "Johannesburg");
        System.out.println(billingAddress);

        shippingAddress = ShippingAddressFactory.buildShippingAddress(LocalTime.of(9, 52), "34 Batersea Drive", "Kibbler park", "2091", "Johannesburg");
        System.out.println(shippingAddress);

        Contact con1 = CustomerContactFactory.buildContact("leroyk@gmail.com", "0739946042", shippingAddress, billingAddress);
        Contact con2 = CustomerContactFactory.buildContact("james@gmail.com", "0739946042", shippingAddress, billingAddress);

        customer1 = CustomerFactory.buildCustomer( "Leroy", "Kulcha", "Liam", "Lkulcha123", con1);
        customer2 = CustomerFactory.buildCustomer( "James", "Ntokozo", "jkulcha456", con2);

        photo = new byte[0];

        publisher1 = PublisherFactory.buildPublisher(34655L, "Marvel", 2000);


        author1 = AuthorFactory.buildAuthor(001L, "Lamark",  "Darwin");
        author2 = AuthorFactory.buildAuthor(002L, "Jacob", "Gedleyihlekisa", "Zuma");

        authors = new ArrayList<>();
        authors.add(author1);
        authors.add(author2);

        Set<Genre> genres1 = Set.of(Genre.FANTASY, Genre.SCI_FI);
        Set<Genre> genres2 = Set.of(Genre.MYSTERY);


        book1 = ComicBookFactory.bookBuilder("Thor", genres1, "AsGuards Prince son of Zuis",
                "B01", 299.99, 2.00, 1, authors, publisher1, LocalDate.of(2022, 03, 04), photo);
        book2 = ComicBookFactory.bookBuilder("Avatar", genres2, "Two Dimension Worls Colliding into one.",
                "B02", 199.99, 1.80, 1, authors, publisher1, LocalDate.of(2024, 03, 15), photo);
        book3 = ComicBookFactory.bookBuilder("HALO", genres1, "GALAXY 2000 years from now",
                "B03", 539.99, 3.50, 3, authors, publisher1, LocalDate.of(2021, 05, 30), photo);

        comicBooks = new ArrayList<>();

        comicBooks.add(book1);
        comicBooks.add(book2);
        comicBooks.add(book3);

        cart1 = CartFactory.buildCart(1L, customer1, comicBooks, LocalDate.of(2023, 02, 15), LocalDate.of(2023, 03, 01));
        cart2 = CartFactory.buildCart(2L, customer2, comicBooks, LocalDate.of(2024, 04, 20), LocalDate.of(2027, 05, 01));
        cart3 = CartFactory.buildCart(3L, customer3, comicBooks, LocalDate.of(2025, 04, 20), LocalDate.of(2024, 05, 01));

    }

    @Test
    void buildCart() {
        assertNotNull(cart1);
        System.out.println(cart1);
    }

    @Test
    void cartWithFutureUpdateDateMustNull() {
        assertNull(cart2);
        System.out.println(cart2);
    }

    @Test
    void cartWithInvalidCustomerMustBeNull() {
        assertNull(cart3);
        System.out.println(cart3);
    }


}