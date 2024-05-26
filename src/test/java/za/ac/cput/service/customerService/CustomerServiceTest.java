package za.ac.cput.service.customerService;

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
class CustomerServiceTest {
    @Autowired
    private CustomerService service;

    @Autowired
    private ComicBookService bookService;

    @Autowired
    private AuthorService authorServic;


    private Customer customer1;
    private Customer customer2;
    private ComicBook book1;
    private ComicBook book2;
    private Cart cart1;
    private Cart cart2;
    private Author author1;
    private Author author2;
    private WishList wishList1;
    private WishList wishList2;
    List<ComicBook> comicBookList;
    List<Author> authors;
    private Publisher publisher1;

    private List<Publisher> publishers;

//    @BeforeEach
//    void setUp() {
//        comicBookList=new ArrayList<>();
//
//        authors = new ArrayList<>();
//        author1 = AuthorFactory.buildAuthor(001, "Lamark", "", "Darwin");
//        authorServic.create(author1);
//        author2 = AuthorFactory.buildAuthor(002, "Jacob", "Gedleyihlekisa", "Zuma");
//        authorServic.create(author1);
//
//        authors.add(author1);
//        //authors.add(author2);
//
//        publisher1 = PublisherFactory.buildPublisher(34655, "Marvel",2000);
//
//
//        book1 = ComicBookFactory.buildBuilder("CMB01", "Thor", 3.4, LocalDate.now(), authors, publisher1, 300.00);
//        book2 = ComicBookFactory.buildBuilder("CMB02", "Hulk", 3.4, LocalDate.of(2024,07,17), authors, publisher1,300.00);
//
//        bookService.create(book1);
//        bookService.create(book2);
//
//
//        comicBookList.add(book1);
//        comicBookList.add(book2);
//
//        cart1 = CartFactory.buildCart(2001,600.00,comicBookList, LocalDate.of(2024,02,15),LocalDate.now());
//        cart2=CartFactory.buildCart(2002, 600,comicBookList,LocalDate.of(2025,04,20),LocalDate.of(2024,05,01));
//
//
//        wishList1 = WishListFactory.buildWishList(1,"myWishList1", comicBookList, LocalDate.of(2024, 02, 14), LocalDate.of(2024, 02, 15));
//        wishList2 = WishListFactory.buildWishList(2,"myWishList2", comicBookList, LocalDate.of(2024, 02, 12), LocalDate.of(2024, 03, 16));
//
//        customer1 = CustomerFactory.buildCustomer(1234, "Lebo", "Jutha", ContactFactory.buildContact("juthal@gmail.com", "0741236547", "0213456789"), cart1, wishList1);
//        customer2 = CustomerFactory.buildCustomer(9875, "Lazi", "Liso", ContactFactory.buildContact("lazil@gmail.com", "0786435498", "0214569879"), cart2, wishList2);;
//    }



    @Test
    @Order(1)
    void create() {
        System.out.println("===========================CREATE========================================");
    Customer savedCustomer = service.create(customer1);
    assertNotNull(savedCustomer);
    System.out.println(savedCustomer);


    }

    @Test
    @Order(2)
    void read() {
        System.out.println("===========================READ========================================");
        Customer readCustomer = service.read(Long.valueOf(1234));
        assertNotNull(readCustomer);
        System.out.println(readCustomer);
    }

    @Test
    @Order(3)
    void update() {
        System.out.println("===========================UPDATE========================================");
        Customer updateCustomer = new Customer.CustomerBuilder().copy(customer1)
                .setFirstName("Matthew")
                .build();

        Customer savedCustomer = service.update(updateCustomer);
        assertEquals(savedCustomer.getCustomerId(), updateCustomer.getCustomerId());
        assertNotNull(savedCustomer);
        System.out.println(savedCustomer);
    }
    @Test
    @Order(4)
    void getall() {
        System.out.println("===========================GETALL========================================");
        System.out.println(service.getall());
    }

}