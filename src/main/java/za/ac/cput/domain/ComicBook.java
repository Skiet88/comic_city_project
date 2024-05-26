package za.ac.cput.domain;
// Mlungisi L. Mbuyazi
// 221164014
// https://github.com/Skiet88/comic__city_project

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity

public class ComicBook {
    @Id
    private String SKU;
    private String name;
    private double wieght;
    private LocalDate releaseDate;

    @JsonProperty
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    private List<Author> authors = new ArrayList<>();

    @JsonProperty
    @ManyToMany(mappedBy = "comicBookList", fetch = FetchType.EAGER)
    private List<Cart> carts = new ArrayList<>();

    @JsonProperty
    @ManyToMany(mappedBy = "comicBooks", fetch = FetchType.EAGER)
    private List<WishList> wishLists = new ArrayList<>();

    private double price;
    @JsonProperty
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.EAGER)
    private Publisher publisher;

    protected ComicBook() {
    }

    private ComicBook(ComicBookBuilder e) {
        this.SKU = e.SKU;
        this.name = e.name;
        this.wieght = e.wieght;
        this.releaseDate = e.releaseDate;
        this.authors = e.authors;
        this.price = e.price;
        this.publisher = e.publisher;
    }



    public String getSKU() {
        return SKU;
    }

    public String getName() {
        return name;
    }

    public double getWieght() {
        return wieght;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public List<Author> getAuthor() {
        return authors;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComicBook comicBook = (ComicBook) o;
        return Double.compare(wieght, comicBook.wieght) == 0 && Double.compare(price, comicBook.price) == 0 && Objects.equals(SKU, comicBook.SKU) && Objects.equals(name, comicBook.name) && Objects.equals(releaseDate, comicBook.releaseDate) && Objects.equals(authors, comicBook.authors) && Objects.equals(carts, comicBook.carts) && Objects.equals(wishLists, comicBook.wishLists) && Objects.equals(publisher, comicBook.publisher);
    }

    @Override
    public int hashCode() {
        return Objects.hash(SKU, name, wieght, releaseDate, authors, carts, wishLists, price, publisher);
    }

    @Override
    public String toString() {
        String authorNames = authors.stream().map(author -> author.getName().getFirstName()).collect(Collectors.joining(", "));
        return "ComicBook{" +
                "SKU='" + SKU + '\'' +
                ", name='" + name + '\'' +
                ", weight=" + wieght +
                ", releaseDate=" + releaseDate +
                ", authors=[" + authorNames + "]" +
                ", price=" + price +
                ", publisher=" + (publisher != null ? publisher.getName() : "No Publisher") +
                '}';
    }

    public static class ComicBookBuilder{
        private String SKU;
        private String name;
        private double wieght;
        private LocalDate releaseDate;
        private List<Author> authors;
        private double price;
        @ManyToOne
        @JoinColumn(name = "publisher_id")

        private Publisher publisher;

        public ComicBookBuilder setPublisher(Publisher publisher) {
            this.publisher = publisher;
            return this;
        }

        public ComicBookBuilder() {
        }

        public ComicBookBuilder setSKU(String SKU) {
            this.SKU = SKU;
            return this;
        }

        public ComicBookBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public ComicBookBuilder setWieght(double wieght) {
            this.wieght = wieght;
            return this;
        }

        public ComicBookBuilder setReleaseDate(LocalDate releaseDate) {
            this.releaseDate = releaseDate;
            return this;
        }

        public ComicBookBuilder setAuthor(List<Author> authors) {
            this.authors = authors;
            return this;
        }

        public ComicBookBuilder setPrice(double price) {
            this.price = price;
            return this;
        }
        public ComicBookBuilder copy(ComicBook e) {
            this.SKU = e.SKU;
            this.name = e.name;
            this.wieght = e.wieght;
            this.releaseDate = e.releaseDate;
            this.authors = e.authors;
            this.price = e.price;
            this.publisher = e.publisher;

            return this;
        }

       public ComicBook build(){

            return new ComicBook(this);
       }
    }

}
