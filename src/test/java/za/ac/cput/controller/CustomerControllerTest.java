package za.ac.cput.controller;


import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import za.ac.cput.domain.*;
import za.ac.cput.factory.BillingAddressFactory;
import za.ac.cput.factory.CustomerContactFactory;
import za.ac.cput.factory.CustomerFactory;
import za.ac.cput.factory.ShippingAddressFactory;
import za.ac.cput.service.customerService.CustomerService;

import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.class)
class CustomerControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CustomerService service;

    private Customer customer1;
    private Customer customer2;

    static  Customer savedCustomer;
    private final String base_url = "http://localhost:8080/comiccity/Customer";
    @BeforeEach
    void setUp() {

        Address billingAddress = BillingAddressFactory.buildBillingAddress("card", "34 Batersea Drive", "Kibbler park", "2091", "Johannesburg");
        System.out.println(billingAddress);

        Address shippingAddress = ShippingAddressFactory.buildShippingAddress(LocalTime.of(9, 52), "33 sea Drive", "Kibbler park 2", "2092", "Johannesburg");
        System.out.println(shippingAddress);

        Contact con1 = CustomerContactFactory.buildContact("leroy9199228@gmail.com", "0739946042", shippingAddress, billingAddress);
        System.out.println(con1);

        Contact con2 = CustomerContactFactory.buildContact("221164014@mycput.ac.za", "0739946042", shippingAddress, billingAddress);

        customer1 = CustomerFactory.buildCustomer( "Leroy", "Kulcha", "Liam", "Lkulcha123", con1);
        customer2 = CustomerFactory.buildCustomer( "James", "Kulcha", "jkulcha456", con2);
    }

    @Test
    @Order(1)
    void create() {
        String url = base_url + "/create";
        ResponseEntity<Customer> postResponse = restTemplate.postForEntity(url, customer1, Customer.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());

        System.out.println("PstR body: " + postResponse);
       savedCustomer = postResponse.getBody();

        System.out.println("Saved data: " + savedCustomer);
    }

    @Test
    @Order(2)
    void read() {
        String url = base_url + "/read/" + savedCustomer.getCustomerId();
        System.out.println("URL: " + url);
        ResponseEntity<Customer> response = restTemplate.getForEntity(url, Customer.class);
        System.out.println("Response: " + response);
        System.out.println("Read data: " + response.getBody());


    }

    @Test
    @Order(3)
    void update() {
        String url = base_url + "/update";
        System.out.println("To be updated: " + savedCustomer);
        Customer updateCustomer = new Customer.CustomerBuilder().copy(savedCustomer)
                .setPassword("Lkulcha456")
                .build();
        System.out.println("updated: " + updateCustomer);

        ResponseEntity<Customer> postResponse = restTemplate.postForEntity(url, updateCustomer, Customer.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());

        Customer updatedCustomer = postResponse.getBody();
        System.out.println("From JPA: " + updatedCustomer);

        assertEquals(updateCustomer.getCustomerId(), updatedCustomer.getCustomerId());
    }

    @Test
    @Order(4)
    void delete() {
        String url = base_url + "/delete/" + Long.valueOf(savedCustomer.getCustomerId());
        System.out.println("URL: " + url);
        restTemplate.delete(url);
        System.out.println("Success: deleted customer");
    }

    @Test
    @Order(5)
    void getall() {
        String url = base_url + "/getall";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET,entity, String.class);
        System.out.println("SHOW ALL:");
        System.out.println(response);
    }
    @Test
    @Order(6)
    void getByEmail() {
        String url = base_url + "/getByEmail/vxayiya@gmail.com" ;
        System.out.println("URL: " + url);
        ResponseEntity<Customer> response = restTemplate.getForEntity(url, Customer.class);
        System.out.println("Response: " + response);
    }
}