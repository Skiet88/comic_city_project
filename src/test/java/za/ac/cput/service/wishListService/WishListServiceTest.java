package za.ac.cput.service.wishListService;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Author;
import za.ac.cput.domain.ComicBook;
import za.ac.cput.domain.Publisher;
import za.ac.cput.domain.WishList;
import za.ac.cput.factory.AuthorFactory;
import za.ac.cput.factory.ComicBookFactory;
import za.ac.cput.factory.PublisherFactory;
import za.ac.cput.factory.WishListFactory;
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
class WishListServiceTest {

    @Autowired
    private WishListService wishListService;
    @Autowired
    AuthorService authorService;

    private WishList wishList1;
    private WishList wishList2;
    private WishList wishList3;

    private Author author1;
    private Author author2;


    private ComicBook book1;
    private ComicBook book2;
    private ComicBook book3;
    private List<Author> authors1;
    private List<Author> authors2;
    private List<ComicBook> comicBookList1;
    private List<ComicBook> comicBookList2;
    private List<ComicBook>comicBookList3;
    private Publisher publisher1;
    private Publisher publisher2;

    @BeforeEach
    void setup() {
        System.out.println("=============================SET-UP====================================");

        publisher1 = PublisherFactory.buildPublisher(67954, "Kat Publishers", 2010);
        publisher2 = PublisherFactory.buildPublisher(87949, "Nathan Publishers", 2007);

        author1 = AuthorFactory.buildAuthor(005, "Mpumzi", "August", "Mbula");
        author2 = AuthorFactory.buildAuthor(006, "James", "Hazen", "Panuel");


        authors1 = new ArrayList<>();
        authors1.add(author1);


        authors2 = new ArrayList<>();
        authors2.add(author2);


        book1 = ComicBookFactory.buildBuilder("CMB01", "Thor", 3.4, LocalDate.now(), authors1, publisher1,300.00);
        System.out.println(book1);


        book2 = ComicBookFactory.buildBuilder("CMB02", "Hulk", 3.4, LocalDate.of(2024, 05, 19), authors2, publisher2,800.00);
        System.out.println(book2);


        book3 = ComicBookFactory.buildBuilder("CMB03", "Spider-Man", 3.4, LocalDate.now(), new ArrayList<>(authors1), publisher1,400);
        System.out.println(book3);



        comicBookList1 = new ArrayList<>();
        comicBookList1.add(book1);



        comicBookList2 = new ArrayList<>();
        comicBookList2.add(book2);

        comicBookList3=new ArrayList<>();
        comicBookList3.add(book3);

        wishList1 = WishListFactory.buildWishList("myWishList1", comicBookList1, LocalDate.of(2024, 02, 14), LocalDate.of(2024, 02, 15));
        wishList2 = WishListFactory.buildWishList("myList", comicBookList2, LocalDate.of(2024, 04, 20), LocalDate.of(2024, 05, 01));
        wishList3 = WishListFactory.buildWishList("Books I Wish To Buy ", comicBookList3, LocalDate.of(2024, 03, 13), LocalDate.of(2024, 05, 01));


    }

    @Test
    @Order(1)
    void create() {
        System.out.println("=============================CREATE====================================");

        WishList wishListCreated = wishListService.create(wishList1);
        assertNotNull(wishListCreated);
        System.out.println(wishListCreated);

        WishList wishListCreated2 = wishListService.create(wishList2);
        assertNotNull(wishListCreated2);
        System.out.println(wishListCreated2);

        WishList wishListCreated3 = wishListService.create(wishList3);
        assertNotNull(wishListCreated3);
        System.out.println(wishListCreated3);

    }

    @Test
    @Order(2)
    void read() {
        System.out.println("=============================Read====================================");
        WishList searchedWishList =wishListService.read(1L);
        assertNotNull(searchedWishList);
        System.out.println(searchedWishList);


    }

    @Test
    @Order(3)
    void update() {
        System.out.println("=============================Update====================================");
        WishList wishListUpdated = new WishList.Builder().copy(wishList3).setUpdatedDate(LocalDate.now()).build();

        WishList updatedWishListDB = wishListService.update(wishListUpdated);
        assertNotNull(updatedWishListDB);
        System.out.println(updatedWishListDB);
    }


    @Test
    @Order(4)
    void delete() {
        System.out.println("=============================DELETE====================================");

        boolean isDeleted= wishListService.delete(Long.valueOf(3));
        assertTrue(isDeleted);
        System.out.println("Is The wishList Deleted?"+isDeleted);
    }

    @Test
    @Order(5)
    void getall() {
        System.out.println("=============================GETALL====================================");
        System.out.println(wishListService.getall());
    }
}