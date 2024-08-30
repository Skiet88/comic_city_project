package za.ac.cput.service.invoiceService;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.*;
import za.ac.cput.factory.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class InvoiceServiceTest {
    @Autowired
    private InvoiceService invoiceService;
    private Invoice invoice1;
    private Invoice invoice2;
    private CustomerOrder customerOrder;
    private List<ComicBook> comicBooks;
    private ComicBook comicBook;
    private Publisher publisher;
    private Author author1;
    private Author author2;
    private List<Author> authors;
    static  Invoice savedInvoice;

    @BeforeEach
    void setUp() {
        System.out.println("=============================SET-UP====================================");

        publisher = PublisherFactory.buildPublisher(5L, "Marvel",2000);

        author1 = AuthorFactory.buildAuthor(001L,"Lamark", "Mike", "Darwin");
        author2 = AuthorFactory.buildAuthor(002L, "Jacob", "Gedleyihlekisa", "Zuma");
        authors = new ArrayList<>();
        authors.add(author1);
        authors.add(author2);

        Set<Genre> genres1 = Set.of(Genre.FANTASY, Genre.SCI_FI);
        comicBook = ComicBookFactory.bookBuilder("Thor", genres1, "AsGuards Prince son of Zuis",
                "B01", 299.99, 2.00, 1, authors, publisher, LocalDate.of(2022, 03, 04), new byte[0]);

        comicBooks = new ArrayList<>();
        comicBooks.add(comicBook);
        Address billingAddress = BillingAddressFactory.buildBillingAddress("card", "34 Batersea Drive", "Kibbler park", "2091", "Johannesburg");
        System.out.println(billingAddress);

        Address shippingAddress = ShippingAddressFactory.buildShippingAddress(LocalTime.of(9,52), "34 Batersea Drive", "Kibbler park", "2091", "Johannesburg");
        System.out.println(shippingAddress);

        Contact con1 = CustomerContactFactory.buildContact("leroyk10@gmail.com", "0739946042", shippingAddress, billingAddress);
        Contact con2 = CustomerContactFactory.buildContact("2-mycput.za", "0739946042",  shippingAddress , billingAddress);

        Customer customer1 = CustomerFactory.buildCustomer("Leroy" , "Kulcha", "Liam","Lkulcha123",con1);
        Customer customer2 = CustomerFactory.buildCustomer("James" , "Kulcha", "","jkulcha456",con2);

        customerOrder = CustomerOrderFactory.buildCustomerOrder(  LocalDate.from(LocalDateTime.now()), comicBooks, customer1 ,650.00);

        invoice1 = InvoiceFactory.buildInvoice(1L, customerOrder, LocalDateTime.now());
        invoice2 = InvoiceFactory.buildInvoice(2L, null, LocalDateTime.now());
    }

    @Test
    @Order(1)
    void create() {
        System.out.println("=============================CREATE====================================");

         savedInvoice = invoiceService.create(invoice1);
        assertNotNull(savedInvoice);
        System.out.println(savedInvoice);
    }

    @Test
    @Order(2)
    void read() {
        System.out.println("=============================READ====================================");

        Invoice readInvoice = invoiceService.read(savedInvoice.getInvoiceId());
        assertNotNull(readInvoice);
        System.out.println(readInvoice);
    }

    @Test
    @Order(3)
    void update() {
        System.out.println("=============================UPDATE====================================");

        Invoice updatedInvoice = new Invoice.InvoiceBuilder().copy(savedInvoice)
                .setDateTimeGenerated(LocalDateTime.now())
                .build();

        Invoice savedInvoice = invoiceService.update(updatedInvoice);
        assertEquals(savedInvoice.getInvoiceId(), updatedInvoice.getInvoiceId());
        assertNotNull(savedInvoice);
        System.out.println(savedInvoice);
    }


    @Test
    @Order(4)
    void getAll() {
        System.out.println("=============================GET-ALL====================================");
        System.out.println(invoiceService.getAll());
    }
    @Test
    @Order(5)
    void delete() {
        System.out.println("=============================DELETE====================================");
        boolean isDeleted = invoiceService.delete(savedInvoice.getInvoiceId());
        assertEquals(true, isDeleted);
        System.out.println("Invoice no:" + invoice1.getInvoiceId() + " Deleted");
    }

}