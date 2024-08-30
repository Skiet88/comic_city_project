package za.ac.cput.service.cartService;

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
class CartServiceTest {
    @Autowired
    private CartService cartService;
    private static Cart cart1;
    private static Cart cart2;
    private static Cart cart3;

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
    private static byte[] photo;

    private static ByteArrayOutputStream out1;
    private static BufferedImage image1;
    private static byte[] photo1;

    private static ByteArrayOutputStream out2;
    private static BufferedImage image2;
    private static byte[] photo2;
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


    @BeforeAll
    static void  setUp() {

        System.out.println("============================SETUP==================================");

        String url1 ="C:\\Users\\User\\Documents\\IntelliJ Projects 2024\\comic_city_project\\images\\ComicBookCover4.jpeg";
        String url2="C:\\Users\\User\\Documents\\IntelliJ Projects 2024\\comic_city_project\\images\\ComicBookCover5.jpeg";
        String url3="C:\\Users\\User\\Documents\\IntelliJ Projects 2024\\comic_city_project\\images\\ComicBookCover6.jpeg";

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

        shippingAddress1 = ShippingAddressFactory.buildShippingAddress(LocalTime.of(2, 52), "34 Batersea Drive", "Kibbler park", "2091", "Johannesburg");
        System.out.println(shippingAddress1);


        billingAddress2 = BillingAddressFactory.buildBillingAddress("card", "5 Mpetsheni Cresent", "Khayelitsha", "7784", "Cape Town");
        System.out.println(billingAddress2);

        shippingAddress2 = ShippingAddressFactory.buildShippingAddress(LocalTime.of(1, 30), "5 Mpetsheni Cresen", "Khayelitsha", "7784", "Cape Town");
        System.out.println(shippingAddress2);

        billingAddress3 = BillingAddressFactory.buildBillingAddress("card", "Ny 121 No 28", "Guguletu", "7750", "Cape Town");
        System.out.println(billingAddress3);

        shippingAddress3 = ShippingAddressFactory.buildShippingAddress(LocalTime.of(1, 37), "Ny 121 No 28", "Guguletu", "7750", "Cape Town");
        System.out.println(shippingAddress3);


        Contact con1 = CustomerContactFactory.buildContact("leroyk2@gmail.com", "0739946042", shippingAddress1, billingAddress1);
        System.out.println(con1);

        Contact con2 = CustomerContactFactory.buildContact("james2@gmail.com", "0739946042", shippingAddress2, billingAddress2);
        System.out.println(con2);


        Contact con3 = CustomerContactFactory.buildContact("vxayiya@gmail.com", "0835805117", shippingAddress3, billingAddress3);

        System.out.println(con3);

        customer1 = CustomerFactory.buildCustomer( "Leroy", "Kulcha", "Liam", "Lkulcha123", con1);
        System.out.println(customer1);

        customer2 = CustomerFactory.buildCustomer( "James", "Ntokozo", "jkulcha456", con2);
        System.out.println(customer2);

        customer3 = CustomerFactory.buildCustomer( "Vuyokazi", "Xayiya", "Mpu@2022!!", con3);
        System.out.println(customer3);

        cart1 = CartFactory.buildCart(1L, customer1, comicBooks1, LocalDate.of(2024, 04, 12), LocalDate.now());
        System.out.println(cart1);

        cart2 = CartFactory.buildCart(2L, customer2, comicBooks2, LocalDate.of(2024, 05, 22), LocalDate.of(2024, 06, 17));
        System.out.println(cart2);

        cart3 = CartFactory.buildCart(3L, customer3, comicBooks3, LocalDate.of(2024, 06, 13), LocalDate.now());
        System.out.println(cart3);

    }


    @Test
    @Order(1)
    void create() {

        System.out.println("============================CREATE==================================");

        Cart cartCreated1 = cartService.create(cart1);
        assertNotNull(cartCreated1);
        System.out.println(cartCreated1);

        Cart cartCreated2 = cartService.create(cart2);
        assertNotNull(cartCreated2);
        System.out.println(cartCreated2);

        Cart cartCreated3 = cartService.create(cart3);
        assertNotNull(cartCreated3);
        System.out.println(cartCreated3);

    }

    @Test
    //@Disabled
    @Order(2)
    void read() {
        System.out.println("============================Read==================================");

        Cart cartRead = cartService.read(2L);
        assertNotNull(cartRead);
        System.out.println(cartRead);
    }

    @Test
    //@Disabled
    @Order(3)
    void update() {
        System.out.println("============================Update==================================");
        Cart cartToUpdate=new Cart.Builder().copy(cart2).setUpdatedDate(LocalDate.now()).build();
        Cart updatedCart=cartService.update(cartToUpdate);
        assertNotNull(updatedCart);
        System.out.println(updatedCart);
    }


    @Test
    //@Disabled
    @Order(4)
    void delete() {
        System.out.println("============================Delete===============================");
        boolean isDeleted=cartService.delete(3L);
        assertTrue(isDeleted);
        System.out.println("Cart with cartId:"+cart3.getCartId()+" is deleted Successfully");
    }

    @Test
    //@Order(5)
    void getall() {
        System.out.println("=============================GetALL==============================");
        List<Cart>carts=cartService.getall();
        assertNotNull(carts);
        System.out.println(carts);
    }

    @Test
    @Disabled
    @Order(6)
    void quantityTest() {
        int quantity=cartService.quantity(1L);
        assertEquals(2,cart2.getComicBooks().size());
        System.out.println("Number of Books on the cart:"+quantity);
    }

    @Test
    //@Disabled
    @Order(7)
    void totalPriceTest() {
        double sum=cartService.getCartTotalPrice(1L);
        double sum2=0;
        for(ComicBook comicBook:cart2.getComicBooks()){
            sum2+=comicBook.getPrice();
        }
        assertEquals(sum,sum2);
        System.out.println("Test Passed:TotalPrices match :\nprice1:R"+sum+"\nprice2:R"+sum2);

    }
    @Test
    //@Disabled
    @Order(8)
    void getCartWithCustomerEmail() {
       Cart cartFetched=cartService.getCartByCustomerEmail("vxayiya@gmail.com");
       assertNotNull(cartFetched);
        System.out.println(cartFetched);


    }
}