package za.ac.cput.service.comicBookService;
// Mlungisi L. Mbuyazi
// 221164014
// https://github.com/Skiet88/comic__city_project
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Author;
import za.ac.cput.domain.ComicBook;
import za.ac.cput.domain.Publisher;
import za.ac.cput.factory.AuthorFactory;
import za.ac.cput.factory.ComicBookFactory;
import za.ac.cput.factory.PublisherFactory;
import za.ac.cput.service.authorService.AuthorService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ComicBookServiceTest {
    @Autowired
    private ComicBookService comicBookService;
    @Autowired
    private AuthorService authorService;
    private ComicBook book1;
    private Author author1;
    private Author author2;
    private List<Author> authors;
    private ComicBook book2;
    private ComicBook book3;

    private Publisher publisher1;

    private List<Publisher> publishers;

    @BeforeEach
    void setUp() {
        System.out.println("=============================SET-UP====================================");
//        author1 = authorService.read(Long.valueOf(001));
//        author2 = authorService.read(Long.valueOf(003));


        author1 = AuthorFactory.buildAuthor(001, "Lamark", "Principle", "Darwin");
        //authorService.create(author1);

        author2 = AuthorFactory.buildAuthor(002, "Jacob", "Gedleyihlekisa", "Zuma");
       // authorService.create(author2);


        authors = new ArrayList<>();
        authors.add(author1);
        //authors.add(author2);

        publisher1 = PublisherFactory.buildPublisher(34655, "Marvel",2000);
        publishers = new ArrayList<>();
        publishers.add(publisher1);

        book1 = ComicBookFactory.buildBuilder("CMB01", "Thor", 3.4, LocalDate.now(), authors, publishers, 300.00);
        System.out.println(book1);
        book2 = ComicBookFactory.buildBuilder("CMB02", "Hulk", 3.4, LocalDate.of(2024,05,17), authors, publishers,300.00);

        System.out.println(book2);
        book3 = ComicBookFactory.buildBuilder("CMB03", "Spider-Man", 3.4, LocalDate.now(), authors, publishers, 221);
        System.out.println(book3);

    }

    @Test
    @Order(1)
    void create() {
        System.out.println("=============================CREATE====================================");
        ComicBook savedComicCook1 = comicBookService.create(book1);
        assertNotNull(savedComicCook1);
        System.out.println(savedComicCook1);

        ComicBook savedComicCook2 = comicBookService.create(book2);
        assertNotNull(savedComicCook2);
        System.out.println(savedComicCook2);

        ComicBook savedComicCook3 = comicBookService.create(book3);
        assertNotNull(savedComicCook3);
        System.out.println(savedComicCook3);
    }

    @Test
    @Order(2)
    void read() {
        System.out.println("=============================READ====================================");
        ComicBook readBook = comicBookService.read(book1.getSKU());
        assertNotNull(readBook);
        System.out.println(readBook);
    }

    @Test
    @Order(3)
    void update() {
        System.out.println("=============================UPDATE====================================");
        authors.add(author2);
        ComicBook updatedComicBook = new ComicBook.ComicBookBuilder().copy(book2)
                .setPrice(880)
                .setAuthor(authors)
                .build();

        ComicBook saved = comicBookService.update(updatedComicBook);
        assertNotNull(saved);
        System.out.println(saved);

    }

    @Test

    @Order(4)
    void delete() {
        System.out.println("=============================DELETE====================================");
        boolean isDeleted = comicBookService.delete(book3.getSKU());
        assertEquals(true, isDeleted);
        System.out.println("Successfully deleted book: " + book3.getName());
    }

    @Test
    @Order(5)
    void getAll() {
        System.out.println("=============================GET-ALL====================================");
        System.out.println(comicBookService.getAll());

    }
}