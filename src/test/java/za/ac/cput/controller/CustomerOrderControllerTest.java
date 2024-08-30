package za.ac.cput.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.junit.jupiter.api.Order;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import za.ac.cput.domain.*;
import za.ac.cput.factory.*;
import za.ac.cput.service.publisherService.PublisherService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CustomerOrderControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    private final String base_url = "http://localhost:8080/comiccity/CustomerOrder";

    private CustomerOrder customerOrder1;
    Author author1;
    Author author2;
    List<Author> authors = new ArrayList<>();

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
    }

    @Test
    @Order(1)
    void a_create() {
        String url = base_url + "/create";
        ResponseEntity<CustomerOrder> postResponse = restTemplate.postForEntity(url, customerOrder1, CustomerOrder.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());

        System.out.println("PstR body: " + postResponse);
        CustomerOrder savedCustomerOrder = postResponse.getBody();
        System.out.println(customerOrder1.getOrderId());
        System.out.println(savedCustomerOrder.getOrderId());
        assertEquals(customerOrder1.getOrderId(), savedCustomerOrder.getOrderId());
        System.out.println("Saved data: " + savedCustomerOrder);
    }

    @Test
    @Order(2)
    void b_read() {
        String url = base_url + "/read/" + customerOrder1.getOrderId();
        System.out.println("URL: " + url);
        ResponseEntity<CustomerOrder> response = restTemplate.getForEntity(url, CustomerOrder.class);
        System.out.println("Response: " + response);
        assertEquals( response.getBody().getOrderId(), customerOrder1.getOrderId());
    }

    @Test
    @Order(3)
    void c_update() {
        String url = base_url + "/update";
        System.out.println(customerOrder1);
        CustomerOrder updatedCustomerOrder = new CustomerOrder.CustomerOrderBuilder().copy(customerOrder1)
                .setTotalAmount(700)
                .build();
        ResponseEntity<CustomerOrder> postResponse = restTemplate.postForEntity(url, updatedCustomerOrder, CustomerOrder.class);
        assertNotNull(postResponse);
        System.out.println("Response: " + postResponse);
        System.out.println("ResponseB: " + postResponse.getBody());
        assertNotNull(postResponse.getBody());

        CustomerOrder updatedCustomer = postResponse.getBody();
        assertEquals(updatedCustomer.getOrderId(), postResponse.getBody().getOrderId());
        System.out.println("Saved data: " + updatedCustomer);
    }

    @Test
    @Order(4)
    void d_delete() {
        String url = base_url + "/delete/" + customerOrder1.getOrderId();
        System.out.println("URL: " + url);
        restTemplate.delete(url);
        System.out.println("Success: deleted customer order");
    }

    @Test
    @Order(5)
    void e_getall() {
        String url = base_url + "/getAll";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET,entity, String.class);
        System.out.println("SHOW ALL:");
        System.out.println(response);
    }
}