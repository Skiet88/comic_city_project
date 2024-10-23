package za.ac.cput.service.customerService;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.*;
import za.ac.cput.factory.*;
import za.ac.cput.service.contactService.ContactService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CustomerServiceTest {
    @Autowired
    private CustomerService service;

    @Autowired
    private ContactService contactService;

    private Customer customer1;
    private Customer customer2;

    private List<Publisher> publishers;
    static Customer savedCustomer;
    static Customer savedCustomer2;
    @BeforeEach
    void setUp() {

        Address billingAddress = BillingAddressFactory.buildBillingAddress("card", "34 Batersea Drive", "Kibbler park", "2091", "Johannesburg");
        System.out.println(billingAddress);

        Address shippingAddress = ShippingAddressFactory.buildShippingAddress(LocalTime.of(9, 52), "33 sea Drive", "Kibbler park 2", "2092", "Johannesburg");
        System.out.println(shippingAddress);

        Address billingAddress2 = BillingAddressFactory.buildBillingAddress("card", "34 sea Drive", "Kibb park 2", "2092", "Johannesburg");
        System.out.println(billingAddress);

        Address shippingAddress2 = ShippingAddressFactory.buildShippingAddress(LocalTime.of(9, 52), "33 sea Drive 2", "Kibbler park 2", "2092", "Johannesburg");
        System.out.println(shippingAddress);

        Contact con1 = CustomerContactFactory.buildContact("leroyk@gmail.com", "0739946042", shippingAddress, billingAddress);
        System.out.println(con1);

        Contact con2 = CustomerContactFactory.buildContact("jimmy2@gmail.com", "0739946042", shippingAddress2, billingAddress2);

        customer1 = CustomerFactory.buildCustomer("Leroy", "Kulcha", "Liam", "l@123", con1);
        customer2 = CustomerFactory.buildCustomer("James", "Kulcha", "Jimmy", "k@123", con2);
    }

        @Test
    @Order(1)
    void create() {
        System.out.println("===========================CREATE========================================");
   contactService.create(customer1.getContact());
    savedCustomer = service.create(customer1);
    assertNotNull(savedCustomer);
    System.out.println(savedCustomer);

    contactService.create(customer2.getContact());
     savedCustomer2 = service.create(customer2);
    assertNotNull(savedCustomer2);
    System.out.println(savedCustomer2);


    }

    @Test
    @Order(2)
    void read() {
        System.out.println("===========================READ========================================");

        Customer readCustomer = service.read(savedCustomer2.getUserId());
        assertNotNull(readCustomer);
        System.out.println(readCustomer);
    }

    @Test
    @Order(3)
    void update() {
        System.out.println("===========================UPDATE========================================");
        Customer updateCustomer = new Customer.CustomerBuilder().copy(savedCustomer)
                .setPassword("lkulcha456")
                .build();

        Customer savedCustomer = service.update(updateCustomer);
        assertEquals(savedCustomer.getUserId(), updateCustomer.getUserId());
        assertNotNull(savedCustomer);
        System.out.println(savedCustomer);
    }
    @Test
    @Order(4)
    void delete(){
        boolean isDeleted = service.delete(Long.valueOf(savedCustomer.getUserId()));
        assertTrue(isDeleted);
        System.out.println("Customer no " + savedCustomer.getUserId() + " deleted");
    }
    @Test
    @Order(5)
    void getall(){
        System.out.println("==================================GET ALL===========================");
        System.out.println(service.getall());
    }
    @Test
    @Order(6)
    void getByEmail(){
        System.out.println("==================================GET By Email===========================");
       Customer customer=service.getCustomerByEmail("vxayiya@gmail.com");
       assertNotNull(customer);
       System.out.println(customer);
    }

}