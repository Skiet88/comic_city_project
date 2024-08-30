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
import za.ac.cput.service.cartService.CartService;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CartControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;
    private final String BASE_URL = "http://localhost:8080/comiccity/cart";
    private static Cart cart1;
    private static ComicBook book1;
    private static Author author1;
    private static List<Author> authors1;
    private static List<ComicBook> comicBooks1;
    private static byte[] photo;
    private static Publisher publisher1;
    private static Customer customer1;
    private static Address billingAddress1;
    private static Address shippingAddress1;
    static Cart savedCart;

    @BeforeAll
    static void setUp() {

        System.out.println("============================SETUP==================================");

        photo = new byte[0];

        //Authors

        author1 = AuthorFactory.buildAuthor(001L, "Lamark", "Principle", "Darwin");

        authors1 = new ArrayList<>();
        authors1.add(author1);

        publisher1 = PublisherFactory.buildPublisher(123L, "Marvel", 2000);

        //Book

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

        //Contact
        Contact con1 = CustomerContactFactory.buildContact("leroyk9999@gmail.com", "0739946042", shippingAddress1, billingAddress1);
        System.out.println(con1);

        customer1 = CustomerFactory.buildCustomer( "Leroy", "Kulcha", "Liam", "Lkulcha123", con1);
        System.out.println(customer1);

        //Cart

        cart1 = CartFactory.buildCart(1L, customer1, comicBooks1, LocalDate.of(2024, 04, 12), LocalDate.now());
        System.out.println(cart1);


    }

    @Test
    @Order(1)
    void create() {
        String url = BASE_URL + "/create";
        ResponseEntity<Cart> postResponse = restTemplate.postForEntity(url, cart1, Cart.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
         savedCart = postResponse.getBody();
        assertEquals(savedCart.getCartId(), postResponse.getBody().getCartId());
        System.out.println("Saved data:" + savedCart);

    }

    @Test
    @Order(2)
    void read() {

        System.out.println("Id to read: " + savedCart.getCartId());
        String url = BASE_URL + "/read/" + savedCart.getCartId();

        ResponseEntity<Cart> response = restTemplate.getForEntity(url, Cart.class);
        assertEquals(response.getBody().getCartId(), savedCart.getCartId());
        System.out.println("Read: " + response.getBody());
    }

    @Test
    @Order(3)
    void update() {
        Cart newCart = new Cart.Builder().copy(savedCart).setUpdatedDate(LocalDate.now()).build();
        String url = BASE_URL + "/update";
        ResponseEntity<Cart> postResponse = restTemplate.postForEntity(url, newCart, Cart.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());

        Cart cartUpdated = postResponse.getBody();
        assertEquals(cartUpdated.getCartId(), newCart.getCartId());
        System.out.println("Updated data:" + cartUpdated);
    }

    @Test
    @Order(7)
    @Disabled
    void delete() {
        String url = BASE_URL + "/delete/" + cart1.getCartId();
        System.out.println("URL: " + url);
        restTemplate.delete(url);
        System.out.println("Success: Deleted cart");

    }

    @Test
    @Order(4)
    void getall() {
        String url = BASE_URL + "/getall";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        System.out.println("Show ALL");
        System.out.println(response);
        System.out.println(response.getBody());

    }

    @Test
    @Order(5)
    void getCartTotalPrice() {
        String url = BASE_URL + "/totalPrice/" + savedCart.getCartId();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Double> entity = new HttpEntity<>(null, headers);
        ResponseEntity<Double> response = restTemplate.exchange(url, HttpMethod.GET, entity, Double.class);
        System.out.println("CartID:" + savedCart.getCartId() + "\nTotal Price:" + response.getBody());
        System.out.println(response);
        System.out.println(response.getBody());

    }

    @Test
    @Order(6)
    void quantity() {
        String url = BASE_URL + "/quantity/" + cart1.getCartId();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Integer> entity = new HttpEntity<>(null, headers);
        ResponseEntity<Integer> response = restTemplate.exchange(url, HttpMethod.GET, entity, Integer.class);
        System.out.println("CartID:" + cart1.getCartId() + "\nNumber of books:" + response.getBody());
        System.out.println(response);
        System.out.println(response.getBody());
    }

    @Test
    @Order(7)
    void addToCartApi() {

        String url=BASE_URL+"/"+2+"/addComicBook/"+8;
        ResponseEntity<Cart> postResponse = restTemplate.postForEntity(url, cart1, Cart.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        System.out.println(postResponse.getBody());
        Cart cartWithNewBook = postResponse.getBody();
        assertEquals(cartWithNewBook.getCartId(), postResponse.getBody().getCartId());
        //assertEquals(2,cartWithNewBook.getComicBooks().size());
        System.out.println("Saved data:" + cartWithNewBook);
    }
    @Test
    @Order(8)
    void removeFromCartApi() {

        String url=BASE_URL+"/"+2+"/removeComicBook/"+10;
        ResponseEntity<Cart> postResponse = restTemplate.postForEntity(url, cart1, Cart.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        System.out.println(postResponse.getBody());
        Cart cartWithNewBook = postResponse.getBody();
        assertEquals(cartWithNewBook.getCartId(), postResponse.getBody().getCartId());
        assertEquals(1,cartWithNewBook.getComicBooks().size());
        System.out.println("Saved data:" + cartWithNewBook);
    }

    @Test
    @Order(9)
    void getCartByCustomerEmail() {
        String url = BASE_URL + "/getCustomerCart/vxayiya@gmail.com" ;
        ResponseEntity<Cart> response = restTemplate.getForEntity(url, Cart.class);
        Cart fetchedcart=response.getBody();
        assertNotNull(fetchedcart);
        System.out.println("Customer's Cart: " + response.getBody());
    }

}