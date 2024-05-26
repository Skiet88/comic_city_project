package za.ac.cput.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import za.ac.cput.domain.Author;
import za.ac.cput.domain.Name;
import za.ac.cput.factory.AuthorFactory;

import java.sql.SQLOutput;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AuthorControllerTest {
    private final String BASE_URL = "http://localhost:8080/comiccitydb/author";
    @Autowired
    private TestRestTemplate restTemplate;

    private Author author1;
    private  Author author2;
    private  Author author3;
    @BeforeEach
    void setUp() {
        System.out.println("=============================SET-UP====================================");

        author1 = AuthorFactory.buildAuthor(001, "Lamark", "Principle", "Darwin");
        System.out.println(author1);
        author2 = AuthorFactory.buildAuthor(002, "Jacob", "Gedleyihlekisa", "Zuma");
        System.out.println(author2);
        author3 = AuthorFactory.buildAuthor(003, "Brown", "", "Chris");
        System.out.println(author3);
    }

    @Test
    @Order(1)
    void create() {
        System.out.println("=============================CREATE====================================");
        String url = BASE_URL + "/create";
         ResponseEntity<Author>  postResponse = restTemplate.postForEntity(url,author1,Author.class);
        ResponseEntity<Author>  postResponse2 = restTemplate.postForEntity(url,author2,Author.class);
        ResponseEntity<Author>  postResponse3 = restTemplate.postForEntity(url,author3,Author.class);

        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        System.out.println(postResponse.getBody().getAuthorID());
        assertEquals(postResponse.getBody().getAuthorID(), author1.getAuthorID());
        System.out.println(postResponse.getBody());

        assertNotNull(postResponse2);
        assertNotNull(postResponse2.getBody());
        assertEquals(postResponse2.getBody().getAuthorID(), author2.getAuthorID());

        System.out.println(postResponse2.getBody());

        assertNotNull(postResponse3);
        assertNotNull(postResponse3.getBody());
        assertEquals(postResponse3.getBody().getAuthorID(), author3.getAuthorID());
        System.out.println(postResponse3.getBody());
    }

    @Test
    @Order(2)
    void read() {
        System.out.println("=============================READ====================================");
        String url = BASE_URL + "/read/" + author2.getAuthorID();
        System.out.println(url);

        ResponseEntity<Author> response = restTemplate.getForEntity(url, Author.class);
        assertEquals(response.getBody().getAuthorID(), author2.getAuthorID());
        System.out.println("Response Read: " + response.getBody());

    }

    @Test
    @Order(3)
    void update() {
        System.out.println("=============================UPDATE====================================");

        String url = BASE_URL + "/update";

        Name updatedNames = new Name.NameBuilder().copy(author3.getName())
                .setFirstName(author3.getName().getLastName())
                .setLastName(author3.getName().getFirstName())
                .build();

        Author updatedAuthor3 = new Author.AuthorBuilder().copy(author3)
                .setName(updatedNames)
                .build();
        ResponseEntity<Author> response = restTemplate.postForEntity(url,updatedAuthor3,Author.class);

        assertNotNull(response);
        assertNotNull(response.getBody());

        assertEquals(response.getBody().getAuthorID(), author3.getAuthorID());
        System.out.println("Updated Author: " + response.getBody());

    }
    @Test
    @Order(4)
    void delete() {
        System.out.println("=============================DELETE====================================");

        String url = BASE_URL + "/delete" + author2.getAuthorID();
        System.out.println(url);
        restTemplate.delete(url);
        System.out.println("Successfully Deleted Author");

    }

    @Test
    @Order(5)
    void getAll() {
        System.out.println("=============================GETALL====================================");
        String url = BASE_URL +  "/getAll";

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response =  restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        System.out.println("All Authors:");
        System.out.println(response);
        System.out.println(response.getBody());


    }
}