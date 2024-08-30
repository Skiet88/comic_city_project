package za.ac.cput.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import za.ac.cput.domain.Author;
import za.ac.cput.domain.ComicBook;
import za.ac.cput.domain.Genre;
import za.ac.cput.domain.Publisher;
import za.ac.cput.factory.AuthorFactory;
import za.ac.cput.factory.ComicBookFactory;
import za.ac.cput.factory.PublisherFactory;
import za.ac.cput.service.authorService.AuthorService;
import za.ac.cput.service.comicBookService.ComicBookService;
import za.ac.cput.service.publisherService.PublisherService;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.Serial;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ComicBookControllerTest {
    private final String BASE_URL = "http://localhost:8080/comiccity/comic_book";

    @Autowired
    private TestRestTemplate testRestTemplate;

    private static ComicBook book1;
    private static Author author1;
    private static Author author2;
    private static List<Author> authors;
    private static ComicBook book2;
    private static ComicBook book3;

    private static Publisher publisher1;

    static byte[] photo;
    static BufferedImage image;
    static ByteArrayOutputStream out;

    static ComicBook savedBook1;
    static ComicBook savedBook2;
    static ComicBook savedBook3;


    @BeforeAll
    static void a_setUp() {
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

        author1 = AuthorFactory.buildAuthor(001L, "Lamark", "Principle", "Darwin");

        author2 = AuthorFactory.buildAuthor(002L, "Jacob", "Gedleyihlekisa", "Zuma");

        authors = new ArrayList<>();
        authors.add(author1);


        publisher1 = PublisherFactory.buildPublisher(34655L, "Marvel", 2000);

        Set<Genre> genres1 = Set.of(Genre.FANTASY, Genre.SCI_FI);
        Set<Genre> genres2 = Set.of(Genre.MYSTERY);


        book1 = ComicBookFactory.bookBuilder("Thor", genres1, "AsGuards Prince son of Zuis",
                "B01", 299.99, 2.00, 1, authors, publisher1, LocalDate.of(2022, 03, 04), photo);
        book2 = ComicBookFactory.bookBuilder("Avatar", genres2, "Two Dimension Worls Colliding into one.",
                "B02", 199.99, 1.80, 1, authors, publisher1, LocalDate.of(2024, 03, 15), photo);
        book3 = ComicBookFactory.bookBuilder("HALO", genres1, "GALAXY 2000 years from now",
                "B03", 539.99, 3.50, 3, authors, publisher1, LocalDate.of(2021, 05, 30), photo);

    }

    @Test
    @Order(1)
    void b_create() {

        String url = BASE_URL + "/create";
        System.out.println(savedBook1);

        //Saving book 1
        ResponseEntity<ComicBook> response1 = testRestTemplate.postForEntity(url, book1, ComicBook.class);
        assertThat(response1.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertNotNull(response1);
        assertNotNull(response1.getBody());
        savedBook1 = response1.getBody();
        System.out.println("Saved Book: " + savedBook1);

        //Saving book 2
        ResponseEntity<ComicBook> response2 = testRestTemplate.postForEntity(url, book2, ComicBook.class);
        assertNotNull(response2);
        assertNotNull(response2.getBody());
        savedBook2 = response2.getBody();
        System.out.println("Saved Book: " + savedBook2);

        //Saving book 3
        ResponseEntity<ComicBook> response3 = testRestTemplate.postForEntity(url, book3, ComicBook.class);
        assertNotNull(response3);
        assertNotNull(response3.getBody());
        savedBook3 = response3.getBody();
        System.out.println("Saved Book: " + savedBook3);


        System.out.println(savedBook1);
    }

    @Test
    @Order(2)
    void c_read() {
        System.out.println(savedBook1);
        String url = BASE_URL + "/read/" + 1;
        System.out.println(url);

        ResponseEntity<ComicBook> response = testRestTemplate.getForEntity(url, ComicBook.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            ComicBook comicBook = response.getBody();
            System.out.println("Received ComicBook: " + comicBook);
        } else if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
            System.out.println("ComicBook not found.");
        } else if (response.getStatusCode() == HttpStatus.BAD_REQUEST) {
            System.out.println("Bad request. Invalid setSKU provided.");
        } else {
            System.out.println("Unexpected error: " + response.getStatusCode());
        }
        assertNotNull(response);
        assertNotNull(response.getBody());


    }

    @Test
    @Order(3)
    void d_update() {
        String url = BASE_URL + "/update";
        ComicBook updatedComicBook = new ComicBook.Builder().copy(savedBook2)
                .setPrice(880)
                .setAuthors(authors)
                .build();

        ResponseEntity<ComicBook> response = testRestTemplate.postForEntity(url, updatedComicBook, ComicBook.class);
        assertNotNull(response);

        assertNotNull(response.getBody());
        assertEquals(response.getBody().getSKU(), savedBook2.getSKU());
        System.out.println(response.getBody());

    }

    @Test
    @Order(4)
    void e_delete() {
        String url = BASE_URL + "/delete/" + savedBook3.getSKU();
        System.out.println("Book");
        System.out.println(savedBook3);
        System.out.println("===========================");
        System.out.println("Url");
        System.out.println(url);

        testRestTemplate.delete(url);

        System.out.println("Book Deleted");

    }

    @Test

    @Order(5)
    void e_getAll() {
        String url = BASE_URL + "/getAll";

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> httpEntity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = testRestTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);

        System.out.println("All Books:");
        System.out.println(response);
        System.out.println(response.getBody());
    }
}