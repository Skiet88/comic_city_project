package za.ac.cput.service.wishListService;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.*;
import za.ac.cput.factory.*;
import za.ac.cput.service.authorService.AuthorService;
import za.ac.cput.service.comicBookService.ComicBookService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class WishListServiceTest {

    @Autowired
    private WishListService wishListService;
    @Autowired
    AuthorService authorService;
    @Autowired
    ComicBookService comicBookService;
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
    private Publisher publisher1;
    private List<Publisher> publishers;

    @BeforeEach
    void setup() {
        System.out.println("=============================SET-UP====================================");

        author1 = AuthorFactory.buildAuthor(005, "Mpumzi", "August", "Mbula");
        author2 = AuthorFactory.buildAuthor(006, "James", "Hazen", "Panuel");

        author1 = authorService.create(author1);
        author1 = authorService.create(author2);

        authors1 = new ArrayList<>();
        authors1.add(author1);

        authors2 = new ArrayList<>();
        authors2.add(author2);

        publisher1 = PublisherFactory.buildPublisher(34655, "Marvel",2000);
        publishers = new ArrayList<>();
        publishers.add(publisher1);


        book1 = ComicBookFactory.buildBuilder("CMB01", "Thor", 3.4, LocalDate.now(), authors1, publishers, 300.00);
        System.out.println(book1);
        book2 = ComicBookFactory.buildBuilder("CMB02", "Hulk", 3.4, LocalDate.of(2024,05,17), authors2, publishers,300.00);
        System.out.println(book2);
        book3 = ComicBookFactory.buildBuilder("CMB03", "Spider-Man", 3.4, LocalDate.now(), authors1, publishers, 221);
        System.out.println(book3);

        comicBookService.create(book1);
        comicBookService.create(book2);
        comicBookService.create(book3);

        comicBookList1 = new ArrayList<>();
        comicBookList1.add(book1);


        comicBookList2 = new ArrayList<>();
        comicBookList2.add(book2);

        wishList1 = WishListFactory.buildWishList(001,"myWishList1", comicBookList1, LocalDate.of(2024, 02, 14), LocalDate.of(2024, 02, 15));
        wishList2 = WishListFactory.buildWishList(002,"myList", comicBookList2, LocalDate.of(2024, 04, 20), LocalDate.of(2024, 05, 01));
        wishList3 = WishListFactory.buildWishList(003,"Books I Wish To Buy ", new ArrayList<>(comicBookList2), LocalDate.of(2024, 03, 13), LocalDate.of(2024, 05, 01));


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
        System.out.println("=============================Update====================================");
        WishList searchedWishList =wishListService.read(1L);
        assertNotNull(searchedWishList);
        System.out.println(searchedWishList);
    }

    @Test
    @Order(3)
    void update() {

        WishList wishListUpdated = new WishList.Builder().copy(wishList2).setUpdatedDate(LocalDate.now()).build();

        WishList updatedWishListDB = wishListService.update(wishListUpdated);
        assertNotNull(updatedWishListDB);
        System.out.println(updatedWishListDB);
    }

    @Test
    @Order(4)
    void delete() {
        System.out.println("=============================DELETE====================================");

        boolean isDeleted= wishListService.delete(Long.valueOf(002));
        assertEquals(true, isDeleted);
        System.out.println("Is The cart Deleted?"+isDeleted);
    }

    @Test
    @Order(5)
    void getall() {

        System.out.println(wishListService.getall());
    }
}