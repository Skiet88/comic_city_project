package za.ac.cput.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
/* Review.java
   Author: Cavan Ramone Swartz (221055835)
   Date: 18 June 2024
   https://github.com/hollow007/my_comic_city_project
 */

@Entity
public class Review {
    @Id
    @JsonProperty("reviewID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewID;

    @ManyToOne
    private ComicBook comicBook;
    @ManyToOne( fetch = FetchType.EAGER)
    private Customer customer;

    private int reviewRating;

    private String reviewDescription;

    private LocalDate reviewDate;

    private String reviewTitle;

    protected Review() {}

    private Review(ReviewBuilder e) {
        this.reviewID = e.reviewID;
        this.customer = e.customer;
        this.comicBook = e.comicBook;
        this.reviewRating = e.reviewRating;
        this.reviewDescription = e.reviewDescription;
        this.reviewDate = e.reviewDate;
        this.reviewTitle = e.reviewTitle;
    }

    public Long getReviewID() {
        return reviewID;
    }

    public ComicBook getComicBook() {
        return comicBook;
    }

    public Customer getCustomer() {
        return customer;
    }

    public int getReviewRating() {
        return reviewRating;
    }

    public String getReviewDescription() {
        return reviewDescription;
    }

    public LocalDate getReviewDate() {
        return reviewDate;
    }

    public String getReviewTitle() {
        return reviewTitle;
    }

    public Review setComicBook(ComicBook comicBook) {
        this.comicBook = comicBook;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass()!= o.getClass()) return false;
        Review review = (Review) o;
        return Objects.equals(reviewID, review.reviewID) && Objects.equals(customer, review.customer) && Objects.equals(comicBook, review.comicBook) && reviewRating == review.reviewRating && Objects.equals(reviewDescription, review.reviewDescription) && Objects.equals(reviewDate, review.reviewDate) && Objects.equals(reviewTitle, review.reviewTitle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reviewID, customer, comicBook, reviewRating, reviewDescription, reviewDate, reviewTitle);
    }

    @Override
    public String toString() {
        return "Review{" +
                "reviewID=" + reviewID +
                ", comicBook=" + comicBook +
                ", customer=" + customer +
                ", reviewRating=" + reviewRating +
                ", reviewDescription='" + reviewDescription + '\'' +
                ", reviewDate=" + reviewDate +
                ", reviewTitle='" + reviewTitle + '\'' +
                '}';
    }

    public static class ReviewBuilder {
        private Long reviewID;
        private Customer customer;
        private ComicBook comicBook;
        private int reviewRating;
        private String reviewDescription;
        private LocalDate reviewDate;
        private String reviewTitle;

        public ReviewBuilder() {

        }

        public ReviewBuilder setReviewID(Long reviewID) {
            this.reviewID = reviewID;
            return this;
        }

        public ReviewBuilder setCustomer(Customer customer) {
            this.customer = customer;
            return this;
        }

        public ReviewBuilder setComicBook(ComicBook comicBook) {
            this.comicBook = comicBook;
            return this;
        }

        public ReviewBuilder setReviewRating(int reviewRating) {
            this.reviewRating = reviewRating;
            return this;
        }

        public ReviewBuilder setReviewDescription(String reviewDescription) {
            this.reviewDescription = reviewDescription;
            return this;
        }

        public ReviewBuilder setReviewDate(LocalDate reviewDate) {
            this.reviewDate = reviewDate;
            return this;
        }

        public ReviewBuilder setReviewTitle(String reviewTitle) {
            this.reviewTitle = reviewTitle;
            return this;
        }

        public ReviewBuilder copy(Review review){
            this.reviewID = review.reviewID;
            this.customer = review.customer;
            this.comicBook = review.comicBook;
            this.reviewRating = review.reviewRating;
            this.reviewDescription = review.reviewDescription;
            this.reviewDate = review.reviewDate;
            this.reviewTitle = review.reviewTitle;
            return this;
        }

        public Review build() {
            return new Review(this);
        }
    }
}