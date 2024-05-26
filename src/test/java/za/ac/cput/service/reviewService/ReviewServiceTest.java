package za.ac.cput.service.reviewService;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import za.ac.cput.domain.Review;
import za.ac.cput.domain.User;
import za.ac.cput.factory.ReviewFactory;
import za.ac.cput.factory.UserFactory;
import za.ac.cput.repository.ReviewRepository;
import za.ac.cput.repository.UserRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

/* ReviewServiceTest.java
   Author: Cavan Ramone Swartz (221055835)
   Date: 19 May 2024
   https://github.com/hollow007/my_comic_city_project
 */
@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ReviewServiceTest {

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private UserRepository userRepository;

//    @InjectMocks
//    private ReviewService reviewService;

    private Review review;
    private User user;

//    @BeforeEach
//    void setUp() {
//        review = ReviewFactory.createReview(890, 95, 4, "", LocalDate.now(), false, "Good read", '4', "Approved" );
//        user = UserFactory.createUser("John Doe", "johndoe@example.com", "password");
//    }

//    @Test
//    @Order(1)
//    void create() {
//        when(userRepository.findById(String.valueOf(1))).thenReturn(Optional.of(user));
//        when(reviewRepository.save(review)).thenReturn(review);
//
////        Review createdReview = reviewService.create(review);
//
//        assertEquals(createdReview.getReviewID(), review.getReviewID());
//        verify(userRepository, times(1)).findById(String.valueOf(1));
//        verify(reviewRepository, times(1)).save(review);
//    }

//    @Test
//    @Order(2)
//    void read() {
//        when(reviewRepository.findById(1)).thenReturn(Optional.of(review));
//
//        Review readReview = reviewService.read(1);
//
//        assertEquals(readReview.getReviewID(), review.getReviewID());
//        verify(reviewRepository, times(1)).findById(1);
//    }

//    @Test
//    @Order(3)
//    void update() {
//        when(reviewRepository.findById(1)).thenReturn(Optional.of(review));
//        when(reviewRepository.save(review)).thenReturn(review);
//
//        Review updatedReview = reviewService.update(review);
//
//        assertEquals(updatedReview.getReviewID(), review.getReviewID());
//        verify(reviewRepository, times(1)).findById(1);
//        verify(reviewRepository, times(1)).save(review);
//    }

//    @Test
//    @Order(4)
//    void delete() {
//        when(reviewRepository.existsById(1)).thenReturn(true);
//
//        boolean deleted = reviewService.delete(1);
//
//        assertTrue(deleted);
//        verify(reviewRepository, times(1)).deleteById(1);
//        verify(reviewRepository, times(1)).existsById(1);
//    }

//    @Test
//    @Order(5)
//    void getAll() {
//        List<Review> reviews = new ArrayList<>();
//        reviews.add(review);
//        when(reviewRepository.findAll()).thenReturn(reviews);
//
//        List<Review> allReviews = reviewService.getAll();
//
//        assertEquals(allReviews.size(), 1);
//        verify(reviewRepository, times(1)).findAll();
//    }

//    @Test
//    @Order(6)
//    void findByComicBookID() {
//        List<Review> reviews = new ArrayList<>();
//        reviews.add(review);
//        when(reviewRepository.findByComicBookID(1)).thenReturn(reviews);
//
//        List<Review> comicBookReviews = reviewService.findByComicBookID(1);
//
//        assertEquals(comicBookReviews.size(), 1);
//        verify(reviewRepository, times(1)).findByComicBookID(1);
//    }


}