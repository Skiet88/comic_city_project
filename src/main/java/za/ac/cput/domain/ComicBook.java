package za.ac.cput.domain;
// Mlungisi L. Mbuyazi
// 221164014
// https://github.com/Skiet88/comic__city_project

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class ComicBook {
    @Id
    private String SKU;
    private String name;
    private double wieght;
    private LocalDate releaseDate;
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    private List<Author> authors ;

//    @ManyToMany(mappedBy = "comicBookList", fetch = FetchType.EAGER)
//    private List<Cart> carts = new ArrayList<>();

//    @ManyToMany(mappedBy="comicBooks", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.EAGER)
//    private List<WishList> wishLists = new ArrayList<>();

    private double price;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.EAGER)
    private List<Publisher> publishers;

    protected ComicBook() {
    }

    private ComicBook(ComicBookBuilder e) {
        this.SKU = e.SKU;
        this.name = e.name;
        this.wieght = e.wieght;
        this.releaseDate = e.releaseDate;
        this.authors = e.authors;
        this.price = e.price;
        this.publishers = e.publishers;
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

    public List getAuthor() {
        return authors;
    }

    public List getPublishers() {
        return publishers;
    }

    public double getPrice() {
        return price;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        ComicBook comicBook = (ComicBook) o;
//        return Double.compare(wieght, comicBook.wieght) == 0 && Double.compare(price, comicBook.price) == 0 && Objects.equals(SKU, comicBook.SKU) && Objects.equals(name, comicBook.name) && Objects.equals(releaseDate, comicBook.releaseDate) && Objects.equals(authors, comicBook.authors) && Objects.equals(carts, comicBook.carts) && Objects.equals(wishLists, comicBook.wishLists) && Objects.equals(publishers, comicBook.publishers);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(SKU, name, wieght, releaseDate, authors, carts, wishLists, price, publishers);
//    }

    @Override
    public String toString() {
        return "ComicBook{" +
                "SKU='" + SKU + '\'' +
                ", name='" + name + '\'' +
                ", wieght=" + wieght +
                ", releaseDate=" + releaseDate +
                ", authors=" + authors +
               // ", carts=" + carts +
                //", wishLists=" + wishLists +
                ", price=" + price +
                ", publishers=" + publishers +
                '}';
    }

    public static class ComicBookBuilder{
        private String SKU;
        private String name;
        private double wieght;
        private LocalDate releaseDate;
        private List<Author> authors;
        private double price;
        private List<Publisher> publishers;

        public ComicBookBuilder setPublishers(List<Publisher> publishers) {
            this.publishers = publishers;
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
            this.publishers = e.publishers;

            return this;
        }

       public ComicBook build(){

            return new ComicBook(this);
       }
    }

}
