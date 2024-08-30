package za.ac.cput.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/* ReviewFactoryTest.java
   Author: Cavan Ramone Swartz (221055835)
   Date: 21 June 2024
   https://github.com/hollow007/my_comic_city_project
 */
class ReviewFactoryTest {

    private Customer customer;
    private ComicBook comicBook;

    private Review review1;
    private Review review2;
    private Review review3;

    private Contact contact;
    private Cart cart;
    private WishList wishlist;
    private Publisher publisher;
    private List<Author> authors;
    private Author author1;
    private Author author2;
    byte[] photo;

    @BeforeEach
    void setUp() {
        photo = new byte[0];

        publisher = PublisherFactory.buildPublisher(5L, "Marvel",2000);

        Address billingAddress = new BillingAddressFactory().
                buildBillingAddress("card", "34 Batersea Drive", "Kibbler park", "2091", "Johannesburg");
        System.out.println(billingAddress);

        Address shippingAddress = new ShippingAddressFactory().buildShippingAddress(LocalTime.parse("10:30:00"), "34 Batersea Drive", "Kibbler park", "2091", "Johannesburg");
        System.out.println(shippingAddress);

        Contact con1 = CustomerContactFactory.buildContact("leroyk@gmail.com", "0739946042", shippingAddress, billingAddress);
        customer = CustomerFactory.buildCustomer("Leroy" , "Kulcha", "Sane","Lkulcha123",con1);


        author1 = AuthorFactory.buildAuthor(001L,"Lamark", "", "Darwin");
        author2 = AuthorFactory.buildAuthor(002L, "Jacob", "Gedleyihlekisa", "Zuma");
        authors = new ArrayList<>();
        authors.add(author1);
        authors.add(author2);

        Set<Genre> genres1 = Set.of(Genre.FANTASY, Genre.SCI_FI);
        comicBook = ComicBookFactory.bookBuilder("Thor", genres1, "AsGuards Prince son of Zuis",
                "B01", 299.99, 2.00, 1, authors, publisher, LocalDate.of(2022, 03, 04), photo);

        review1 = ReviewFactory.buildReview(1L, customer, comicBook, 4, "This is a great comic book!", LocalDate.now(), "Review 1");
        review2 = ReviewFactory.buildReview(2L, customer, comicBook, 0, "This is a bad comic book!", LocalDate.of(2024, 07, 17), "Review 2");
        review3 = ReviewFactory.buildReview(3L, null, comicBook, 5, "This is a great comic book!", LocalDate.now(), "Review 3");
    }

    @Test
    void buildReview() {
        Review review1 = ReviewFactory.buildReview(1L, customer, comicBook, 4, "This is a great comic book!", LocalDate.now(), "Review 1");
        assertNotNull(review1);
        System.out.println(review1);
    }

    @Test
    void buildReviewWithInvalidRatingMustFail() {
        assertNull(review2);
        System.out.println(review2);
    }

    @Test
    void buildReviewWithNullCustomerMustFail() {
        assertNull(review3);
        System.out.println(review3);
    }
}