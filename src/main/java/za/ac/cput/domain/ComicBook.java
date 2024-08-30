package za.ac.cput.domain;
// Mlungisi L. Mbuyazi
// 221164014
// https://github.com/Skiet88/comic__city_project


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.action.internal.OrphanRemovalAction;

import java.time.LocalDate;
import java.util.*;

import java.util.List;

@Entity
@Table(name = "comic_books")
public class ComicBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long sku;

    private String name;

    private String description;

    private double weight;

    private LocalDate releaseDate;

    //@ManyToMany(fetch = FetchType.EAGER,cascade = { CascadeType.REMOVE })
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "comic_book_author",
            joinColumns = @JoinColumn(name = "comic_book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<Author> authors ;

    @ManyToMany(mappedBy = "comicBooks",cascade = CascadeType.ALL)
    private List<Cart> carts ;

    @ManyToMany(mappedBy = "comicBooks",cascade = CascadeType.ALL)
    private List<WishList> wishLists ;

    private double price;

    private int quantity;

    @JsonProperty("isbn")
    private String ISBN;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    @ElementCollection(targetClass = Genre.class ,fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "comic_book_genres", joinColumns = @JoinColumn(name = "comic_book_id"))
    @Column(name = "genre")
    private Set<Genre> genres;  // Multiple genre

    @Lob
    @Column(length=100000)
    private byte[] photo;
    protected ComicBook() {

    }


    private ComicBook(Builder builder) {
        this.sku = builder.SKU;
        this.name = builder.name;
        this.description = builder.description;
        this.weight = builder.weight;
        this.releaseDate = builder.releaseDate;
        this.authors = builder.authors;
        this.price = builder.price;
        this.quantity = builder.quantity;
        this.ISBN = builder.ISBN;
        this.publisher = builder.publisher;
        this.genres = builder.genres;
        this.photo = builder.photo;
    }


    public Long getSKU() { return sku; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public double getWeight() { return weight; }
    public LocalDate getReleaseDate() { return releaseDate; }
    @JsonIgnore
    public List<Author> getAuthors() { return authors; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public String getISBN() { return ISBN; }
    @JsonIgnore
    public Publisher getPublisher() { return publisher; }

    public Set<Genre> getGenres() {
        return genres;
    }

    public byte[] getPhoto() {
        return photo;
    }

    @Override
    public String toString() {
        return "ComicBook{" +
                "sku=" + sku +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", weight=" + weight +
                ", releaseDate=" + releaseDate +
                ", authors=" + authors +
                ", price=" + price +
                ", quantity=" + quantity +
                ", ISBN='" + ISBN + '\'' +
                ", publisher=" + publisher +
                ", genres=" + genres +
               // ", photo=" + Arrays.toString(photo) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComicBook comicBook = (ComicBook) o;
        return Double.compare(weight, comicBook.weight) == 0 && Double.compare(price, comicBook.price) == 0 && quantity == comicBook.quantity && Objects.equals(sku, comicBook.sku) && Objects.equals(name, comicBook.name) && Objects.equals(description, comicBook.description) && Objects.equals(releaseDate, comicBook.releaseDate) && Objects.equals(authors, comicBook.authors) && Objects.equals(carts, comicBook.carts) && Objects.equals(wishLists, comicBook.wishLists) && Objects.equals(ISBN, comicBook.ISBN) && Objects.equals(publisher, comicBook.publisher) && Objects.equals(genres, comicBook.genres) && Objects.deepEquals(photo, comicBook.photo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sku, name, description, weight, releaseDate, authors, carts, wishLists, price, quantity, ISBN, publisher, genres, Arrays.hashCode(photo));
    }

    public static class Builder {
        private Long SKU;
        private String name;
        private String description;
        private double weight;
        private LocalDate releaseDate;
        private List<Author> authors;
        private double price;
        private int quantity;
        private String ISBN;
        private Publisher publisher;
        private byte[] photo;
        private Set<Genre> genres;


        public Builder setSKU(Long SKU) {
            this.SKU = SKU;
            return this;
        }

       public Builder(){}

        public Builder copy(ComicBook c) {
            this.SKU = c.sku;
            this.name = c.name;
            this.description = c.description;
            this.weight = c.weight;
            this.releaseDate = c.releaseDate;
            this.authors = c.authors;
            this.price = c.price;
            this.quantity = c.quantity;
            this.ISBN = c.ISBN;
            this.publisher = c.publisher;
            this.genres = c.genres;
            this.photo = c.photo;

            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setGenres(Set<Genre> genres) {
            this.genres = genres;
            return this;
        }

        public Builder setWeight(double weight) {
            this.weight = weight;
            return this;
        }

        public Builder setReleaseDate(LocalDate releaseDate) {
            this.releaseDate = releaseDate;
            return this;
        }

        public Builder setAuthors(List<Author> authors) {
            this.authors = authors;
            return this;
        }

        public Builder setPrice(double price) {
            this.price = price;
            return this;
        }

        public Builder setQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder setISBN(String ISBN) {
            this.ISBN = ISBN;
            return this;
        }

        public Builder setPublisher(Publisher publisher) {
            this.publisher = publisher;
            return this;
        }

        public Builder setPhoto(byte[] photo) {
            this.photo = photo;
            return this;
        }

        public ComicBook build() {
            return new ComicBook(this);
        }
    }
}

