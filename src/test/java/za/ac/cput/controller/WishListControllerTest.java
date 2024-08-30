package za.ac.cput.controller;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import za.ac.cput.domain.*;
import za.ac.cput.factory.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WishListControllerTest {
    @Autowired
    private  TestRestTemplate testRestTemplate;
    private static final String BASE_URL = "http://localhost:8080/comiccity/wishList";

    private static WishList wishList1;
    private static ComicBook book1;

    private static Author author1;

    private static List<Author> authors1;

    private static List<ComicBook> comicBooks1;
    private static byte[] photo;

    private static Publisher publisher1;
    private static Customer customer1;

    private static Address billingAddress1;
    private static Address shippingAddress1;
    static  WishList wishListCreated;

    @BeforeAll
    static void setUp() {

        photo = new byte[0];
        System.out.println("============================SETUP==================================");


        //Authors
        author1 = AuthorFactory.buildAuthor(001L, "Lamark", "Principle", "Darwin");


        authors1 = new ArrayList<>();
        authors1.add(author1);

        publisher1 = PublisherFactory.buildPublisher(1234L, "Marvel", 2000);

        Set<Genre> genres1 = Set.of(Genre.FANTASY, Genre.SCI_FI);


        book1 = ComicBookFactory.bookBuilder("Thor", genres1, "AsGuards Prince son of Zuis",
                "B01", 299.99, 2.00, 1, authors1, publisher1, LocalDate.of(2022, 03, 04), photo);

        comicBooks1 = new ArrayList<>();
        comicBooks1.add(book1);

//Addresses
        billingAddress1 = BillingAddressFactory.buildBillingAddress("card", "34 Batersea Drive", "Kibbler park", "2091", "Johannesburg");
        System.out.println(billingAddress1);

        shippingAddress1 = ShippingAddressFactory.buildShippingAddress(LocalTime.of(9, 52), "34 Batersea Drive", "Kibbler park", "2091", "Johannesburg");
        System.out.println(shippingAddress1);


        Contact con1 = CustomerContactFactory.buildContact("leroyk2@gmail.com", "0739946042", shippingAddress1, billingAddress1);
        System.out.println(con1);

        customer1 = CustomerFactory.buildCustomer( "Leroy", "Kulcha", "Liam", "Lkulcha123", con1);
        System.out.println(customer1);

        wishList1 = WishListFactory.buildWishList(9L, "My Fave", customer1, comicBooks1, LocalDate.of(2024, 04, 12), LocalDate.now());
        System.out.println(wishList1);

    }

    @Test
    @Order(1)
    void create() {
        String url = BASE_URL + "/create";
        ResponseEntity<WishList> response = testRestTemplate.postForEntity(url, wishList1, WishList.class);
        assertNotNull(response);
        assertNotNull(response.getBody());
         wishListCreated = response.getBody();
        System.out.println("WishList Created: " + wishListCreated);
    }

    @Test
    @Order(2)
    void read() {
        String url = BASE_URL + "/read/" + wishListCreated.getWishListId();
        System.out.println(url);
        ResponseEntity<WishList> response = testRestTemplate.getForEntity(url, WishList.class);
        WishList readWishList=response.getBody();
        assertEquals(readWishList.getWishListId(), response.getBody().getWishListId());
        System.out.println("Read: " + response.getBody());
    }

    @Test
    @Order(3)
    void update() {
        String url=BASE_URL+"/update";
        WishList newWishList=new WishList.Builder().copy(wishListCreated).setWishListName("Surprise For My Baby Mo").build();
        System.out.println(newWishList);

        ResponseEntity<WishList>postResponse=testRestTemplate.postForEntity(url,newWishList,WishList.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());

        WishList wishListUpdated=postResponse.getBody();
        assertEquals(postResponse.getBody(),wishListUpdated);
        System.out.println(wishListUpdated);
    }

    @Test
    @Order(4)
    @Disabled
    void delete() {
        String url=BASE_URL+"/delete/"+wishListCreated.getWishListId();
        testRestTemplate.delete(url);
        System.out.println("WishList with ID:"+wishListCreated.getWishListId()+" is deleted!");
    }

    @Test
    @Order(5)
    void getall() {


        String url = BASE_URL + "/getall";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = testRestTemplate.exchange(url, HttpMethod.GET,entity, String.class);
        System.out.println("SHOW ALL:");
        System.out.println(response);
    }

    @Test
    @Order(6)
    void quantity() {
        String url = BASE_URL + "/quantity/" + wishListCreated.getWishListId();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Integer> entity = new HttpEntity<>(null, headers);
        ResponseEntity<Integer> response = testRestTemplate.exchange(url, HttpMethod.GET, entity, Integer.class);
        System.out.println("WishListID:" + wishList1.getWishListId() + "\nNumber of books:" + response.getBody());
        System.out.println(response);
        System.out.println(response.getBody());
    }
}