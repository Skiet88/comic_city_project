package za.ac.cput.service.customerOrderService;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.*;
import za.ac.cput.factory.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CustomerOrderServiceTest {

    @Autowired
    private CustomerOrderService customerOrderService;

    private CustomerOrder customerOrder1;
    private CustomerOrder customerOrder2;
    private CustomerOrder customerOrder3;
    Author author1;
    Author author2;
    List<Author> authors = new ArrayList<>();

    static  CustomerOrder savedCustomerOrder;

    @BeforeEach
    void setUp() {
        byte[] photo = new byte[0];

        Publisher publisher1 = PublisherFactory.buildPublisher(34655L, "Marvel",2000);


        author1 = AuthorFactory.buildAuthor(001L,"Lamark",  "Darwin");
        System.out.println("Author 1: " + author1);
        author2 = AuthorFactory.buildAuthor(002L, "Jacob", "Gedleyihlekisa", "Zuma");
        System.out.println("Author 2: " + author2);

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

        Contact con1 = CustomerContactFactory.buildContact("leroyy1@gmail.com", "0739946042", shippingAddress, billingAddress);
        Contact con2 = CustomerContactFactory.buildContact("221164014@mycput.ac.za", "0739946042",  shippingAddress , billingAddress);

        Customer customer1 = CustomerFactory.buildCustomer("Leroy" , "Kulcha", "Liam","Lkulcha123",con1);
        Customer customer2 = CustomerFactory.buildCustomer("James" , "Kulcha", "","jkulcha456",con2);


        customerOrder1 = CustomerOrderFactory.buildCustomerOrder(  LocalDate.of(2022, 03, 04), comicBooks, customer1, 650.00);
    }

    @Test
    @Order(1)
    void create() {
        System.out.println("===========================CREATE========================================");
         savedCustomerOrder = customerOrderService.create(customerOrder1);
        assertNotNull(savedCustomerOrder);
        System.out.println("Saved Order: " + customerOrder1);
    }

    @Test
    @Order(2)
    void read() {
        System.out.println("===========================READ========================================");
        CustomerOrder readCustomerOrder = customerOrderService.read(savedCustomerOrder.getOrderId());
        assertNotNull(readCustomerOrder);
        System.out.println("Read Order: " + readCustomerOrder);
    }

    @Test
    @Order(3)
    void update() {
        System.out.println("===========================UPDATE========================================");
        CustomerOrder updateCustomerOrder = new CustomerOrder.CustomerOrderBuilder().copy(savedCustomerOrder)
                .setTotalAmount(850)
                .build();

        CustomerOrder savedCustomerOrder = customerOrderService.update(updateCustomerOrder);
        assertEquals(savedCustomerOrder.getOrderId(), updateCustomerOrder.getOrderId());
        assertNotNull(savedCustomerOrder);
        System.out.println(savedCustomerOrder);
    }


    @Test
    @Order(4)
    void getall(){
        System.out.println("==================================GET ALL===========================");
        System.out.println(customerOrderService.getall());
    }

    @Test
    @Order(5)
    void delete() {
        boolean isDeleted = customerOrderService.delete(savedCustomerOrder.getOrderId());
        assertTrue(isDeleted);
        System.out.println("CustomerOrder no " + savedCustomerOrder.getOrderId() + " deleted");
    }
}