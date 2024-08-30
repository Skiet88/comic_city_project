package za.ac.cput.factory;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerFactoryTest {


// Before running test always check if the shipping address time and change it to before the current time if its after
    private static  Customer customer1;
    private static Customer customer2;
    @BeforeAll
    public static void setUp(){

        Address billingAddress = BillingAddressFactory.buildBillingAddress("card", "34 Batersea Drive", "Kibbler park", "2091", "Johannesburg");
        System.out.println(billingAddress);

        Address shippingAddress = ShippingAddressFactory.buildShippingAddress(LocalTime.of(9,52), "34 Batersea Drive", "Kibbler park", "2091", "Johannesburg");
        System.out.println(shippingAddress);

        Contact con1 = CustomerContactFactory.buildContact("leroyk@gmail.com", "0739946042", shippingAddress, billingAddress);
        Contact con2 = CustomerContactFactory.buildContact("2-mycput.za", "0739946042",  shippingAddress , billingAddress);

        customer1 = CustomerFactory.buildCustomer("Leroy" , "Kulcha", "Liam","Lkulcha123",con1);
        customer2 = CustomerFactory.buildCustomer("James" , "Kulcha", "","jkulcha456",con2);

    }

    @Test
    void buildCustomer() {
        assertNotNull(customer1);
        System.out.println(customer1);
    }
    @Test

    void failingBuildCustomer(){
        assertNotNull(customer2);
        System.out.println(customer2);
    }
}