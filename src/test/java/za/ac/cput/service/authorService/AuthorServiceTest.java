package za.ac.cput.service.authorService;
// Mlungisi L. Mbuyazi
// 221164014
// https://github.com/Skiet88/comic__city_project
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Author;
import za.ac.cput.domain.Name;
import za.ac.cput.factory.AuthorFactory;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AuthorServiceTest {
    @Autowired
    private AuthorService service;
    private Author author1;
    private  Author author2;
    private  Author author3;

    @BeforeEach
    void setUp() {
        System.out.println("=============================SET-UP====================================");

        author1 = AuthorFactory.buildAuthor(001L, "Lamark", "Principle", "Darwin");
        System.out.println(author1);
        author2 = AuthorFactory.buildAuthor(002L, "Jacob", "Gedleyihlekisa", "Zuma");
        System.out.println(author2);
        author3 = AuthorFactory.buildAuthor(003L, "Brown",  "Chris");
        System.out.println(author3);
    }

    @Test
    @Order(1)
    void create() {
        System.out.println("=============================CREATE====================================");

        Author savedAuthor1 = service.create(author1);
        assertNotNull(savedAuthor1);
        System.out.println(savedAuthor1);

        Author savedAuthor2 = service.create(author2);
        assertNotNull(savedAuthor2);
        System.out.println(savedAuthor2);

        Author savedAuthor3 = service.create(author3);
        assertNotNull(savedAuthor3);
        System.out.println(savedAuthor3);

    }

    @Test
    @Order(2)
    void read() {
        System.out.println("=============================READ====================================");

        Author readAuthor1 = service.read(Long.valueOf(001));
        assertNotNull(readAuthor1);
        System.out.println(readAuthor1);
    }

    @Test
    @Order(3)
    void update() {
        System.out.println("=============================UPDATE====================================");

        Name updatedNames = new Name.NameBuilder().copy(author3.getName())
                .setFirstName(author3.getName().getLastName())
                .setLastName(author3.getName().getFirstName())
                .build();

        Author updatedAuthor3 = new Author.AuthorBuilder().copy(author3)
                .setName(updatedNames)
                .build();

        Author savedAuthor = service.update(updatedAuthor3);
        assertEquals(savedAuthor.getAuthorID(), author3.getAuthorID());
        assertNotNull(savedAuthor);
        System.out.println(savedAuthor);

    }

    @Test
    @Order(4)
    void delete() {
        System.out.println("=============================DELETE====================================");
        boolean isDeleted = service.delete(Long.valueOf(author2.getAuthorID()));
        assertEquals(true, isDeleted);
        System.out.println("Author no:"+ author2.getAuthorID()+" Deleted");
    }

    @Test
    @Order(5)
    void getAll() {
        System.out.println("=============================GET-ALL====================================");
        System.out.println(service.getAll());
    }

    @Test
    @Order(6)
    void getAllBynAME() {
        System.out.println("=============================GET-ALL====================================");
        System.out.println(service.searchByName("C"));
    }

}