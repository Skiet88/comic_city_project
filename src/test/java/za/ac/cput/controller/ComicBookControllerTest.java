package za.ac.cput.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import za.ac.cput.domain.Author;
import za.ac.cput.domain.ComicBook;
import za.ac.cput.domain.Publisher;
import za.ac.cput.factory.AuthorFactory;
import za.ac.cput.factory.ComicBookFactory;
import za.ac.cput.factory.PublisherFactory;
import za.ac.cput.service.authorService.AuthorService;
import za.ac.cput.service.publisherService.PublisherService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment =  SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ComicBookControllerTest {
    private final String BASE_URL = "http://localhost:8080/comiccitydb/comic_book";

    @Autowired
    private AuthorService authorService;

    @Autowired
    PublisherService publisherService;

    private ComicBook book1;
    private Author author1;
    private Author author2;
    private List<Author> authors;
    private ComicBook book2;
    private ComicBook book3;

    private Publisher publisher1;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @BeforeEach
    void setUp() {
        System.out.println("=============================SET-UP====================================");
        author1 = AuthorFactory.buildAuthor(001, "Lamark", "Principle", "Darwin");
        authorService.create(author1);

        author2 = AuthorFactory.buildAuthor(002, "Jacob", "Gedleyihlekisa", "Zuma");
        authorService.create(author2);

        authors = new ArrayList<>();
        authors.add(author1);

        publisher1 = PublisherFactory.buildPublisher(34655, "Marvel",2000);
        publisherService.create(publisher1);

        book1 = ComicBookFactory.buildBuilder("CMB01", "Thor", 3.4, LocalDate.now(), authors, publisher1, 300.00);
        System.out.println(book1);
        book2 = ComicBookFactory.buildBuilder("CMB02", "Hulk", 3.4, LocalDate.of(2024,05,17), authors, publisher1,300.00);

        System.out.println(book2);
        book3 = ComicBookFactory.buildBuilder("CMB03", "Spider-Man", 3.4, LocalDate.now(), authors, publisher1, 221);
        System.out.println(book3);

    }

    @Test
    @Order(1)
    void create() {
        String url = BASE_URL + "/create";


        System.out.println( book1.getPublisher().getName());
        ResponseEntity<ComicBook> response1 = testRestTemplate.postForEntity(url, book1, ComicBook.class);
       assertNotNull(response1);

       assertNotNull(response1.getBody());
        System.out.println(response1.getBody());
       assertEquals(response1.getBody().getSKU(), book1.getSKU());

       ResponseEntity<ComicBook> response2 = testRestTemplate.postForEntity(url, book1, ComicBook.class);
        assertNotNull(response2);

        assertNotNull(response2.getBody());

        assertEquals(response2.getBody().getSKU(), book2.getSKU());

       ResponseEntity<ComicBook> response3 = testRestTemplate.postForEntity(url, book1, ComicBook.class);
        assertNotNull(response3);

        assertNotNull(response3.getBody());
        assertEquals(response3.getBody().getSKU(), book3.getSKU());
    }

    @Test
    @Order(3)
    void read() {
        String url = BASE_URL + "/read/" + book1.getSKU();

        ResponseEntity<ComicBook> response = testRestTemplate.getForEntity(url, ComicBook.class);
        assertNotNull(response);

        assertNotNull(response.getBody());
        assertEquals(response.getBody().getSKU(), book1.getSKU());
        System.out.println("Read Book: " + response.getBody());

    }

    @Test
    @Order(3)
    void update() {
        String url = BASE_URL + "/update";
        ComicBook updatedComicBook = new ComicBook.ComicBookBuilder().copy(book2)
                .setPrice(880)
                .setAuthor(authors)
                .build();

        ResponseEntity<ComicBook> response = testRestTemplate.postForEntity(url, updatedComicBook, ComicBook.class);
        assertNotNull(response);

        assertNotNull(response.getBody());
        assertEquals(response.getBody().getSKU(), book2.getSKU());

    }

    @Test
    @Order(4)
    void delete() {
        String url = BASE_URL + "/delete/" + book3.getSKU();

        testRestTemplate.delete(url);

        System.out.println("Book Deleted");

    }

    @Test
    @Order(5)
    void getAll() {
        String url = BASE_URL + "/getAll";

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> httpEntity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response= testRestTemplate.exchange(url, HttpMethod.GET, httpEntity ,String.class);

        System.out.println("All Books:");
        System.out.println(response);
        System.out.println(response.getBody());
    }
}