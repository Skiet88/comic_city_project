package za.ac.cput.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.ShippingAddress;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class ShippingAddressFactoryTest {

    ShippingAddress shippingAddress1;
    ShippingAddress shippingAddress2;
    ShippingAddress shippingAddress3;

    @BeforeEach
    void setUp() {

        shippingAddress1 = ShippingAddressFactory.buildShippingAddress(LocalTime.parse("20:00:00"),"210LoopSteert","Cape Town","8000","Cape Town");
    }

    @Test
    void a_goodShippingAddress() {

        shippingAddress2 = ShippingAddressFactory.buildShippingAddress(LocalTime.parse("18:00:00"),"210LoopSteert","Cape Town","8000","Cape Town");
        assertNotNull(shippingAddress2);
        System.out.println(shippingAddress2);
    }


    @Test
    void b_badShippingAddress() {

        shippingAddress3 = ShippingAddressFactory.buildShippingAddress(LocalTime.parse("18:00:00"),"","Cape Town","8000","Cape Town");
        assertNull(shippingAddress3);
        System.out.println(shippingAddress3);
    }

}