package za.ac.cput.service.wishListService;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.*;
import za.ac.cput.factory.*;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class WishListServiceTest {
    @Autowired
    private WishListService wishListService;

    private static WishList wishList1;
    private static WishList wishList2;
    private static WishList wishList3;

    private static ComicBook book1;
    private static ComicBook book2;
    private static ComicBook book3;

    private static Author author1;
    private static Author author2;
    private static Author author3;

    private static List<Author> authors1;
    private static List<Author> authors2;
    private static List<Author> authors3;

    private static List<ComicBook> comicBooks1;
    private static List<ComicBook> comicBooks2;
    private static List<ComicBook> comicBooks3;

    private static ByteArrayOutputStream out;
    private static BufferedImage image;
    private static ByteArrayOutputStream out1;
    private static BufferedImage image1;

    private static ByteArrayOutputStream out2;
    private static BufferedImage image2;


    private static Publisher publisher1;
    private static Publisher publisher2;
    private static Publisher publisher3;
    private static Customer customer1;
    private static Customer customer2;
    private static Customer customer3;
    private static Address billingAddress1;
    private static Address shippingAddress1;
    private static Address billingAddress2;
    private static Address shippingAddress2;
    private static Address billingAddress3;
    private static Address shippingAddress3;
    static  WishList wishListCreated1;
    static  WishList wishListCreated2;
    static  WishList wishListCreated3;


    @BeforeAll
    static void setUp() {

        System.out.println("============================SETUP==================================");

        String url1 = "ComicBookCover4.jpeg";
        String url2 = "ComicBookCover5.jpeg";
        String url3 = "ComicBookCover6.jpeg";

        try {

            image = ImageIO.read(new File(url1));
            out = new ByteArrayOutputStream();
            ImageIO.write(image, "jpeg", out);

            image1 = ImageIO.read(new File(url2));
            out1 = new ByteArrayOutputStream();
            ImageIO.write(image1, "jpeg", out1);

            image2 = ImageIO.read(new File(url3));
            out2 = new ByteArrayOutputStream();
            ImageIO.write(image2, "jpeg", out2);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


        //Authors
        author1 = AuthorFactory.buildAuthor(001L, "Nombulelo", "", "Mbula");

        author2 = AuthorFactory.buildAuthor(002L, "Joyce", "Brandan", "Candance");

        author3 = AuthorFactory.buildAuthor(003L, "Kruben", "", "Naidoo");


        authors1 = new ArrayList<>();
        authors1.add(author1);

        authors2 = new ArrayList<>();
        authors2.add(author2);

        authors3 = new ArrayList<>();
        authors3.add(author3);


        publisher1 = PublisherFactory.buildPublisher(1234L, "SA Comics", 2018);
        publisher2 = PublisherFactory.buildPublisher(5678L, "CPUTComics", 1994);
        publisher3 = PublisherFactory.buildPublisher(9101L, "EkasiComics", 2020);
//Books

        book1 = ComicBookFactory.bookBuilder("GENERATION X", "Thriller", "A super villean on a quest",
                "B04", 200.99, 2.00, 7, authors1, publisher1, LocalDate.of(2024, 07, 04), out.toByteArray());
        book2 = ComicBookFactory.bookBuilder("City Man", "Sci-Fi", "A scientific experiment disaster creates a superhero",
                "B05", 896.99, 4.80, 6, authors1, publisher2, LocalDate.of(2000, 02, 26), out1.toByteArray());
        book3 = ComicBookFactory.bookBuilder("Save US", "Fantasy", "A blud hungry villean turn hero",
                "B06", 1439.99, 3.50, 3, authors2, publisher3, LocalDate.of(1956, 05, 20), out2.toByteArray());

        comicBooks1 = new ArrayList<>();
        comicBooks1.add(book1);
        comicBooks1.add(book3);

        comicBooks2 = new ArrayList<>();
        comicBooks2.add(book1);
        comicBooks2.add(book2);

        comicBooks3 = new ArrayList<>();
        comicBooks3.add(book2);
        comicBooks3.add(book3);

        //Authors
        author1 = AuthorFactory.buildAuthor(001L, "Lamark", "Principle", "Darwin");


        author2 = AuthorFactory.buildAuthor(002L, "Jacob", "Gedleyihlekisa", "Zuma");


        author3 = AuthorFactory.buildAuthor(003L, "Mpumzi", "John", "Mbula");


        authors1 = new ArrayList<>();
        authors1.add(author1);

        authors2 = new ArrayList<>();
        authors2.add(author2);

        authors3 = new ArrayList<>();
        authors3.add(author3);


        publisher1 = PublisherFactory.buildPublisher(1234L, "Marvel", 2000);
        publisher2 = PublisherFactory.buildPublisher(5678L, "80 Fox", 1997);
        publisher3 = PublisherFactory.buildPublisher(9101L, "DC Comics", 1910);
//Books


        Set<Genre> genres1 = Set.of(Genre.FANTASY, Genre.SCI_FI);
        Set<Genre> genres2 = Set.of(Genre.MYSTERY);


        book1 = ComicBookFactory.bookBuilder("Thor", genres1, "AsGuards Prince son of Zuis",
                "B01", 299.99, 2.00, 1, authors1, publisher1, LocalDate.of(2022, 03, 04),out.toByteArray());
        book2 = ComicBookFactory.bookBuilder("Avatar", genres2, "Two Dimension Worls Colliding into one.",
                "B02", 199.99, 1.80, 1, authors1, publisher2, LocalDate.of(2024, 03, 15),out1.toByteArray());
        book3 = ComicBookFactory.bookBuilder("HALO", genres1, "GALAXY 2000 years from now",
                "B03", 539.99, 3.50, 3, authors2, publisher3, LocalDate.of(2021, 05, 30),  out3.toByteArray());


        comicBooks1 = new ArrayList<>();
        comicBooks1.add(book1);
        comicBooks1.add(book3);

        comicBooks2 = new ArrayList<>();
        comicBooks2.add(book1);
        comicBooks2.add(book2);

        comicBooks3 = new ArrayList<>();
        comicBooks3.add(book2);
        comicBooks3.add(book3);

//Addresses
        billingAddress1 = BillingAddressFactory.buildBillingAddress("card", "34 Batersea Drive", "Kibbler park", "2091", "Johannesburg");
        System.out.println(billingAddress1);

        shippingAddress1 = ShippingAddressFactory.buildShippingAddress(LocalTime.of(9, 52), "34 Batersea Drive", "Kibbler park", "2091", "Johannesburg");
        System.out.println(shippingAddress1);


        billingAddress2 = BillingAddressFactory.buildBillingAddress("card", "5 Mpetsheni Cresent", "Khayelitsha", "7784", "Cape Town");
        System.out.println(billingAddress2);

        shippingAddress2 = ShippingAddressFactory.buildShippingAddress(LocalTime.of(6, 30), "5 Mpetsheni Cresen", "Khayelitsha", "7784", "Cape Town");
        System.out.println(shippingAddress2);

        billingAddress3 = BillingAddressFactory.buildBillingAddress("card", "Ny 121 No 28", "Guguletu", "7750", "Cape Town");
        System.out.println(billingAddress3);

        shippingAddress3 = ShippingAddressFactory.buildShippingAddress(LocalTime.of(5, 37), "Ny 121 No 28", "Guguletu", "7750", "Cape Town");
        System.out.println(shippingAddress3);


        Contact con1 = CustomerContactFactory.buildContact("leroyy9@gmail.com", "0739946042", shippingAddress1, billingAddress1);
        System.out.println(con1);

        Contact con2 = CustomerContactFactory.buildContact("james2@gmail.com", "0739946042", shippingAddress2, billingAddress2);
        System.out.println(con2);

        Contact con3 = CustomerContactFactory.buildContact("vxayiya1@gmail.com", "0835805117", shippingAddress3, billingAddress3);
        System.out.println(con3);

        customer1 = CustomerFactory.buildCustomer("Leroy", "Kulcha", "Liam", "Lkulcha123", con1);
        System.out.println(customer1);

        customer2 = CustomerFactory.buildCustomer( "James", "Ntokozo", "jkulcha456", con2);
        System.out.println(customer2);

        customer3 = CustomerFactory.buildCustomer("Vuyokazi", "Xayiya", "Mpu@2022!!", con3);
        System.out.println(customer3);

        wishList1 = WishListFactory.buildWishList(1L, "My Fave", customer1, comicBooks1, LocalDate.of(2024, 04, 12), LocalDate.now());
        System.out.println(wishList1);

        wishList2 = WishListFactory.buildWishList(2L, "Birthday Gift", customer2, comicBooks2, LocalDate.of(2024, 05, 22), LocalDate.of(2024, 06, 17));
        System.out.println(wishList2);

        wishList3 = WishListFactory.buildWishList(3L, "My WishList", customer3, comicBooks3, LocalDate.of(2024, 06, 13), LocalDate.now());
        System.out.println(wishList3);

    }


    @Test
    @Order(1)
    void create() {

        System.out.println("============================CREATE==================================");

        wishListCreated1 = wishListService.create(wishList1);
        assertNotNull(wishListCreated1);
        System.out.println(wishListCreated1);

         wishListCreated2 = wishListService.create(wishList2);
        assertNotNull(wishListCreated2);
        System.out.println(wishListCreated2);

         wishListCreated3 = wishListService.create(wishList3);
        assertNotNull(wishListCreated3);
        System.out.println(wishListCreated3);

    }

    @Test
    @Order(2)
    void read() {
        System.out.println("============================Read==================================");
        System.out.println("To read :" + wishListCreated1.getWishListId());
        WishList wishListRead = wishListService.read(wishListCreated1.getWishListId());
        assertNotNull(wishListRead);
        System.out.println(wishListRead);
    }

    @Test
    @Order(3)
    void update() {
        System.out.println("============================Update==================================");

        WishList wishListToUpdate=new WishList.Builder().copy(wishListCreated2).setUpdatedDate(LocalDate.now()).build();
        WishList updatedWishList=wishListService.update(wishListToUpdate);

        assertNotNull(updatedWishList);
        System.out.println(updatedWishList);
    }


    @Test
    @Order(4)
    void delete() {
        System.out.println("============================Delete===============================");

        boolean isDeleted=wishListService.delete(wishListCreated3.getWishListId());

        assertTrue(isDeleted);
        System.out.println("WishList with wishListId:" + wishList3.getWishListId() + " is deleted Successfully");
    }

    @Test
    @Order(5)
    void getall() {
        List<WishList> wishLists = wishListService.getall();
        assertNotNull(wishLists);
        System.out.println(wishLists);
    }

    @Test
    @Order(6)
    void quantityTest() {
        int quantity = wishListService.calculateQuantity(2L);
        assertEquals(quantity, wishList2.getComicBooks().size());
        System.out.println("Test Passed:\n" + "Quantity:" + quantity + " matches:" + wishList2.getComicBooks().size());
    }

    @Test
    @Order(7)
    void getWishListWithCustomerEmail() {
        WishList wishList = wishListService.getWishListWithCustomerEmail("vxayiya@gmail.com");
        assertNotNull(wishList);
        System.out.println(wishList);


    }
}