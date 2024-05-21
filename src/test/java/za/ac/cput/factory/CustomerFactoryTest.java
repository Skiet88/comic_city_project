package za.ac.cput.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerFactoryTest {

    private Customer customer1;
    private Customer customer2;

    private ComicBook book1;
    private ComicBook book2;
    private Cart cart1;
    private Cart cart2;
    private Author author1;
    private Author author2;
    private WishList wishList1;
    List<ComicBook> comicBookList;
    List<Author> authors;

    private Publisher publisher1;

    private List<Publisher> publishers;
    @BeforeEach
    void setUp() {
        comicBookList=new ArrayList<>();

        authors = new ArrayList<>();
        author1 = AuthorFactory.buildAuthor(001, "Lamark", "", "Darwin");
        author2 = AuthorFactory.buildAuthor(002, "Jacob", "Gedleyihlekisa", "Zuma");

        authors.add(author1);
        authors.add(author2);

        publisher1 = PublisherFactory.buildPublisher(34655, "Marvel",2000);
        publishers = new ArrayList<>();
        publishers.add(publisher1);

        book1 = ComicBookFactory.buildBuilder("CMB01", "Thor", 3.4, LocalDate.now(), authors, publishers, 300.00);
        book2 = ComicBookFactory.buildBuilder("CMB02", "Hulk", 3.4, LocalDate.of(2024,07,17), authors, publishers,300.00);

        comicBookList.add(book1);
        comicBookList.add(book2);

        cart1 = CartFactory.buildCart(600.00,comicBookList, LocalDate.of(2024,02,15),LocalDate.now());
        cart2=CartFactory.buildCart(600,comicBookList,LocalDate.of(2025,04,20),LocalDate.of(2024,05,01));

        wishList1 = WishListFactory.buildWishList(001, "myWishList1", comicBookList, LocalDate.of(2024, 02, 14), LocalDate.of(2024, 02, 15));

        customer1 = CustomerFactory.buildCustomer(1234, "Mbasa", "Cabane",
                ContactFactory.buildContact("mbasac@gmail.com", "0741236547", "0213456789"),
                cart1, wishList1);
        customer2 = customer1;
    }

    @Test
    void buildCustomer() {
        assertNotNull(customer1);
        System.out.println(customer1);
    }
    @Test
    void identityTest(){
        assertSame(customer1, customer2);
        System.out.println(customer2);
    }
}