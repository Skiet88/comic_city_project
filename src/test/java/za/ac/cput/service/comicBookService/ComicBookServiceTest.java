package za.ac.cput.service.comicBookService;
// Mlungisi L. Mbuyazi
// 221164014
// https://github.com/Skiet88/comic_city_project
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Author;
import za.ac.cput.domain.ComicBook;
import za.ac.cput.domain.Genre;
import za.ac.cput.domain.Publisher;
import za.ac.cput.factory.AuthorFactory;
import za.ac.cput.factory.ComicBookFactory;
import za.ac.cput.factory.PublisherFactory;
import za.ac.cput.service.authorService.AuthorService;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.security.PublicKey;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

    byte[] photo;
    static BufferedImage image;
    static ByteArrayOutputStream out;
    Set<Genre> genres1 = Set.of(Genre.FANTASY, Genre.SCI_FI);
    Set<Genre> genres2 = Set.of(Genre.MYSTERY);


    @BeforeEach
    void setUp() {
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
        //authorService.create(author1);
        author2 = AuthorFactory.buildAuthor(002L, "Jacob", "Gedleyihlekisa", "Zuma");
       // authorService.create(author2);
        authors = new ArrayList<>();
        authors.add(author1);
        //authors.add(author2);



        publisher1 = PublisherFactory.buildPublisher(34655L, "Marvel",2000);


        genres1 = Set.of(Genre.FANTASY, Genre.SCI_FI);
        genres2 = Set.of(Genre.MYSTERY);

        book1 = ComicBookFactory.bookBuilder("Thor", genres1, "AsGuards Prince son of Zuis",
                "B01", 299.99, 2.00, 1, authors, publisher1, LocalDate.of(2022, 03, 04), photo);
        book2 = ComicBookFactory.bookBuilder("Avatar", genres2, "Two Dimension Worls Colliding into one.",
                "B02", 199.99, 1.80, 1, authors, publisher1, LocalDate.of(2024, 03, 15), photo);
        book3 = ComicBookFactory.bookBuilder("HALO", genres1, "GALAXY 2000 years from now",
                "B03", 539.99, 3.50, 3, authors, publisher1, LocalDate.of(2021, 05, 30), photo);


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
        ComicBook readBook = comicBookService.read(1L);
        assertNotNull(readBook);
        System.out.println(readBook);


        ByteArrayInputStream inputStream = new ByteArrayInputStream(readBook.getPhoto());

        try {
            BufferedImage bufferedImage1=ImageIO.read(inputStream);
            ImageIO.write(bufferedImage1,"jpeg", new File("output.jpeg"));

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(3)
    void update() {
        System.out.println("=============================UPDATE====================================");
        authors.add(author2);
        ComicBook updatedComicBook = new ComicBook.Builder().copy(book2)
                .setPrice(880)
                .setAuthors(authors)
                .build();

        ComicBook saved = comicBookService.update(updatedComicBook);
        assertNotNull(saved);
        System.out.println(saved);

    }

    @Test

    @Order(4)

    void delete() {
        System.out.println("=============================DELETE====================================");
        boolean isDeleted = comicBookService.delete(3L);
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
