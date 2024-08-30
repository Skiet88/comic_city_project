package za.ac.cput.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.web.client.RestClientException;
import za.ac.cput.domain.*;
import za.ac.cput.factory.*;

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

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ReviewControllerTest {
    private final String BASE_URL = "http://localhost:8080/comiccity/review";

    @Autowired
    private TestRestTemplate testRestTemplate;

    private static Review review1;
    private static Customer customer;
    private static ComicBook comicBook;
    private static Publisher publisher;
    private static List<Author> authors;
    private static Author author1;
    private static byte[] photo;
    private static ByteArrayOutputStream out;
    private static BufferedImage image;;

    static Review savedReview;

    @BeforeAll
    static void setUp() {
        System.out.println("=============================SET-UP====================================");
        String url = "download.jpeg";
        try {
            image = ImageIO.read(new File(url));
            out = new ByteArrayOutputStream();
            ImageIO.write(image, "jpeg", out);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        photo = out.toByteArray();
        System.out.println(photo);
        publisher = PublisherFactory.buildPublisher(5L, "Marvel", 2000);

        Address billingAddress = BillingAddressFactory.buildBillingAddress("EFT", "34 Batersea Drive", "Kibbler park", "2091", "Johannesburg");

        Address shippingAddress = ShippingAddressFactory.buildShippingAddress(LocalTime.parse("22:00:00"), "34 Batersea Drive", "Kibbler park", "2091", "Johannesburg");

        Contact con1 = CustomerContactFactory.buildContact("leroyukh@gmail.com", "0739946042", shippingAddress, billingAddress);
        customer = CustomerFactory.buildCustomer( "Leroy", "Kulcha", "Sane", "Lkulcha123", con1);

        author1 = AuthorFactory.buildAuthor(001L, "Lamark", "Mike", "Darwin");
        authors = new ArrayList<>();
        authors.add(author1);

        Set<Genre> genres1 = Set.of(Genre.FANTASY, Genre.SCI_FI);
        ComicBook comicBook1 = ComicBookFactory.bookBuilder("Thor", genres1, "AsGuards Prince son of Zuis",
                "B01", 299.99, 2.00, 1, authors, publisher, LocalDate.of(2022, 03, 04), photo);

        review1 = ReviewFactory.buildReview( customer, comicBook1, 4, "Good book", LocalDate.now(), "Good book review");
    }

    @Test
    @Order(1)
    void create() {
        String url = BASE_URL + "/create";
            ResponseEntity<Review> response = testRestTemplate.postForEntity(url, review1, Review.class);
            assertNotNull(response);
            assertNotNull(response.getBody());
            savedReview = response.getBody();
            System.out.println("Review Created:");
            System.out.println("Review ID: " + savedReview.getReviewID());
            System.out.println("Review Title: " + savedReview.getReviewTitle());
            System.out.println("Review Description: " + savedReview.getReviewDescription());
    }

    @Test
    @Order(2)
    void read() {
        String url = BASE_URL + "/read/" + savedReview.getReviewID();
        ResponseEntity<Review> response = testRestTemplate.getForEntity(url, Review.class);
        Review readReview = response.getBody();
        assertEquals(readReview.getReviewID(), response.getBody().getReviewID());
        System.out.println("Read: " + response.getBody());
    }

    @Test
    @Order(3)
    void update() {
        String url = BASE_URL + "/update";
        Review newReview = new Review.ReviewBuilder().copy(savedReview)
                .setReviewTitle("Doomsday")
                .setReviewDescription("Super villian of the MCU")
                .build();
        System.out.println(newReview);

        ResponseEntity<Review> postResponse = testRestTemplate.postForEntity(url, newReview, Review.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());

        Review reviewUpdated = postResponse.getBody();
        assertEquals(postResponse.getBody(), reviewUpdated);
        System.out.println(reviewUpdated);
    }

    @Test
    @Order(4)
    @Disabled
    void delete() {
        String url = BASE_URL + "/delete/" + savedReview.getReviewID();
        testRestTemplate.delete(url);
        System.out.println("Review with ID:" + savedReview.getReviewID() + " is deleted!");
    }

    @Test
    @Order(5)
    void getAll() {
        String url = BASE_URL + "/getAll";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = testRestTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        System.out.println("SHOW ALL:");
        System.out.println(response);
    }
}