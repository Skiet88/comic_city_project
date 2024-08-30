package za.ac.cput.factory;

import za.ac.cput.domain.ComicBook;
import za.ac.cput.domain.Customer;
import za.ac.cput.domain.Review;
import java.time.LocalDate;
import za.ac.cput.util.Helper;

/* ReviewFactory.java
   Author: Cavan Ramone Swartz (221055835)
   Date: 20 June 2024
   https://github.com/hollow007/my_comic_city_project
 */
public class ReviewFactory {
    public static Review buildReview(Long reviewID, Customer customer, ComicBook comicBook, int reviewRating, String reviewDescription, LocalDate reviewDate, String reviewTitle) {
        if (Helper.isObjectNull(customer) || Helper.isObjectNull(comicBook) || Helper.isStringNullorEmpty(reviewDescription) || Helper.isStringNullorEmpty(reviewTitle) || reviewRating < 1 || reviewRating > 5 || Helper.isValidDate(reviewDate)) {
            return null;
        }

        return new Review.ReviewBuilder()
                .setReviewID(reviewID)
                .setCustomer(customer)
                .setComicBook(comicBook)
                .setReviewRating(reviewRating)
                .setReviewDescription(reviewDescription)
                .setReviewDate(reviewDate)
                .setReviewTitle(reviewTitle)
                .build();
    }
    public static Review buildReview(Customer customer, ComicBook comicBook, int reviewRating, String reviewDescription, LocalDate reviewDate, String reviewTitle) {
        if (Helper.isObjectNull(customer) || Helper.isObjectNull(comicBook) || Helper.isStringNullorEmpty(reviewDescription) || Helper.isStringNullorEmpty(reviewTitle) || reviewRating < 1 || reviewRating > 5 || Helper.isValidDate(reviewDate)) {
            return null;
        }

        return new Review.ReviewBuilder()

                .setCustomer(customer)
                .setComicBook(comicBook)
                .setReviewRating(reviewRating)
                .setReviewDescription(reviewDescription)
                .setReviewDate(reviewDate)
                .setReviewTitle(reviewTitle)
                .build();
    }
}