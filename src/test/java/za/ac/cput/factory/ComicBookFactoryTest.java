package za.ac.cput.factory;
// Mlungisi L. Mbuyazi
// 221164014
// https://github.com/Skiet88/comic__city_project
//

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Author;
import za.ac.cput.domain.ComicBook;
import za.ac.cput.domain.Genre;
import za.ac.cput.domain.Publisher;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class ComicBookFactoryTest {

    private Author author1;
    private Author author2;

    private Publisher publisher1;
    private List<Author> authors;

    private ComicBook book1;
    private ComicBook book2;
    private ComicBook book3;
    byte[] photo;

    static BufferedImage image;
    static ByteArrayOutputStream out;


    @BeforeEach
    void setUp() {

        String url = "C:\\Users\\ASUS\\OneDrive - Cape Peninsula University of Technology\\Documents\\3rd-Year\\ADP3\\comic_city_project\\download.jpeg";
        try {

            image = ImageIO.read(new File(url));
            out = new ByteArrayOutputStream();
            ImageIO.write(image, "jpeg", out);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        photo = out.toByteArray();
        System.out.println(photo);



        publisher1 = PublisherFactory.buildPublisher(34655L, "Marvel",2000);


        author1 = AuthorFactory.buildAuthor(001L,"Lamark", "", "Darwin");
        author2 = AuthorFactory.buildAuthor(002L, "Jacob", "Gedleyihlekisa", "Zuma");

        authors = new ArrayList<>();
        authors.add(author1);
        authors.add(author2);

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
    void buildBuilder() {
        System.out.println(book1);
        assertNotNull(book1);


    }

    @Test
    void buildBuilderWithFutureDateMustFail() {
        assertNull(book2);
        System.out.println(book2);

    }
    @Test
    void buildBuilderWithInvalidPriceMustFail() {
        assertNull(book3);
        System.out.println(book3);

    }
}