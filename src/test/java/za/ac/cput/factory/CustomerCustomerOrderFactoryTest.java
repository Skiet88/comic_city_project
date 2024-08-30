package za.ac.cput.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CustomerCustomerOrderFactoryTest {

    private CustomerOrder customerOrder1;
    private CustomerOrder customerOrder2;
    private CustomerOrder customerOrder3;
    Author author1;
    Author author2;
    List<Author> authors = new ArrayList<>();
    private static  Customer customer1;
    private static Customer customer2;
    @BeforeEach
    void setUp() {
        byte[] photo = new byte[0];

        Publisher publisher1 = PublisherFactory.buildPublisher(34655L, "Marvel",2000);


        author1 = AuthorFactory.buildAuthor(001L,"Lamark", "", "Darwin");
        author2 = AuthorFactory.buildAuthor(002L, "Jacob", "Gedleyihlekisa", "Zuma");

        authors = new ArrayList<>();
        authors.add(author1);
        authors.add(author2);

        Set<Genre> genres1 = Set.of(Genre.FANTASY, Genre.SCI_FI);
        ComicBook comicBook1 = ComicBookFactory.bookBuilder("Thor", genres1, "AsGuards Prince son of Zuis",
                "B01", 299.99, 2.00, 1, authors, publisher1, LocalDate.of(2022, 03, 04), photo);

//        System.out.println(comicBook1);
        ArrayList<ComicBook> comicBooks = new ArrayList<>();
        comicBooks.add(comicBook1);

        Address billingAddress = BillingAddressFactory.buildBillingAddress("card", "34 Batersea Drive", "Kibbler park", "2091", "Johannesburg");
        System.out.println(billingAddress);

        Address shippingAddress = ShippingAddressFactory.buildShippingAddress(LocalTime.of(9,52), "34 Batersea Drive", "Kibbler park", "2091", "Johannesburg");
        System.out.println(shippingAddress);

        Contact con1 = CustomerContactFactory.buildContact("leroyk@gmail.com", "0739946042", shippingAddress, billingAddress);
        Contact con2 = CustomerContactFactory.buildContact("2-mycput.za", "0739946042",  shippingAddress , billingAddress);

        Customer customer1 = CustomerFactory.buildCustomer("Leroy" , "Kulcha", "Liam","Lkulcha123",con1);
        Customer customer2 = CustomerFactory.buildCustomer("James" , "Kulcha", "","jkulcha456",con2);

          customerOrder1 = CustomerOrderFactory.buildCustomerOrder( LocalDate.of(2022, 03, 04), comicBooks, customer1,650.00);
//        customerOrder2 = CustomerOrderFactory.buildCustomerOrder("ORD002", 2L,LocalDate.of(2024, 7, 17), Arrays.asList(comicBook1, comicBook2), 650.00);
//        customerOrder3 = CustomerOrderFactory.buildCustomerOrder("ORD003", 2L,LocalDate.now(), Arrays.asList(comicBook1, comicBook2), -100.00);
    }

    @Test
    void buildCustomerOrderWithValidDetails() {
        System.out.println(customerOrder1);
        assertNotNull(customerOrder1);
        System.out.println(customerOrder1);
    }

    @Test
    void buildCustomerOrderWithFutureDateMustFail() {
        assertNull(customerOrder2);
        System.out.println(customerOrder2);
    }

    @Test
    void buildCustomerOrderWithInvalidTotalAmountMustFail() {
        assertNull(customerOrder3);
        System.out.println(customerOrder3);
    }
}
