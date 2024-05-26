package za.ac.cput.service.cartService;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Author;
import za.ac.cput.domain.Cart;
import za.ac.cput.domain.ComicBook;
import za.ac.cput.domain.Publisher;
import za.ac.cput.factory.AuthorFactory;
import za.ac.cput.factory.CartFactory;
import za.ac.cput.factory.ComicBookFactory;
import za.ac.cput.factory.PublisherFactory;
import za.ac.cput.service.authorService.AuthorService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Mpumzi Mbula
 * 219053324
 * 19/05/2024
 */
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CartServiceTest {
    @Autowired
    CartService cartService;

    @Autowired
    AuthorService authorService;
    Cart cart1;
    Cart cart2;
    Cart cart3;

    private Author author1;
    private Author author2;
    Publisher publisher1;
    Publisher publisher2;
    private ComicBook book1;
    private ComicBook book2;
    private ComicBook book3;
    private List<Author> authors1;
    private List<Author> authors2;
    private List<ComicBook> comicBookList1;
    private List<ComicBook> comicBookList2;
    private List<ComicBook>comicBookList3;
//
//    @BeforeEach
//    void setUp() {
//        System.out.println("=============================SET-UP====================================");
//
//
//        publisher1 = PublisherFactory.buildPublisher(67954, "Kat Publishers", 2010);
//        publisher2 = PublisherFactory.buildPublisher(87949, "Nathan Publishers", 2007);
//
//        author1 = AuthorFactory.buildAuthor(001, "Lamark", "Principle", "Darwin");
//        author2 = AuthorFactory.buildAuthor(003, "Brown", "", "Chris");
//
//
//        authors1 = new ArrayList<>();
//        authors1.add(author1);
//
//
//        authors2 = new ArrayList<>();
//        authors2.add(author2);
//
//
//        book1 = ComicBookFactory.buildBuilder("CMB01", "Thor", 3.4, LocalDate.now(), authors1, publisher1,300.00);
//        System.out.println(book1);
//
//        book2 = ComicBookFactory.buildBuilder("CMB02", "Hulk", 3.4, LocalDate.of(2024,05,19), authors2,publisher1,800.00);
//        System.out.println(book2);
//
//        book3 = ComicBookFactory.buildBuilder("CMB03", "Spider-Man", 3.4, LocalDate.now(), new ArrayList<>(authors1), publisher2,400);
//        System.out.println(book3);
//
//
//        comicBookList1 = new ArrayList<>();
//        comicBookList1.add(book1);
//
//
//        comicBookList2 = new ArrayList<>();
//        comicBookList2.add(book2);
//
//        comicBookList3=new ArrayList<>();
//        comicBookList3.add(book3);
//
//        cart1 = CartFactory.buildCart( 200.00, comicBookList1, LocalDate.of(2024, 02, 15), LocalDate.now());
//        assertNotNull(cart1);
//        System.out.println(cart1);
//
//        cart2 = CartFactory.buildCart( 450, comicBookList2, LocalDate.of(2024, 04, 20), LocalDate.of(2024, 05, 01));
//        assertNotNull(cart2);
//        System.out.println(cart2);
//
//        cart3 = CartFactory.buildCart( 3450, comicBookList3, LocalDate.of(2024, 01, 20), LocalDate.of(2024, 05, 02));
//        assertNotNull(cart3);
//        System.out.println(cart3);
//    }

    @Test
    @Order(1)
    void create() {
        System.out.println("=============================CREATE====================================");

        Cart createdCart1 = cartService.create(cart1);
        assertNotNull(createdCart1);
        System.out.println(createdCart1);

        Cart createdCart2 = cartService.create(cart2);
        assertNotNull(createdCart2);
        System.out.println(createdCart2);

        Cart createdCart3=cartService.create(cart3);
        assertNotNull(createdCart3);
        System.out.println(createdCart3);
    }


    @Test
    @Order(2)
    void read() {
        System.out.println("=============================Update====================================");
        Cart searchedCart = cartService.read(Long.valueOf(2));
        assertNotNull(searchedCart);
        System.out.println(searchedCart);
    }

    @Test
    @Order(3)
    void update() {

        Cart cartUpdated = new Cart.Builder().copy(cart2).setUpdatedDate(LocalDate.now()).build();

        Cart updatedCartDB = cartService.update(cartUpdated);
        assertNotNull(updatedCartDB);
        System.out.println(updatedCartDB);
    }

    @Test
    @Order(4)
    void delete() {
        System.out.println("=============================DELETE====================================");

        boolean exists= cartService.delete(Long.valueOf(3));
        assertTrue(exists);
        System.out.println("Is The cart Deleted?"+exists);


    }

    @Test
    @Order(5)
    void getall() {

        System.out.println(cartService.getall());

    }
}