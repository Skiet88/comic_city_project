package za.ac.cput.factory;
// Mlungisi L. Mbuyazi
// 221164014
// https://github.com/Skiet88/comic__city_project

import za.ac.cput.domain.Author;
import za.ac.cput.domain.ComicBook;
import za.ac.cput.domain.Genre;
import za.ac.cput.domain.Publisher;
import za.ac.cput.util.Helper;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class ComicBookFactory {
    public static ComicBook bookBuilder(String name, Set<Genre> genres, String description, String ISBN, double price, double weight,
                                        int quantity, List<Author> authors, Publisher publisher, LocalDate releaseDate, byte[] photo) {
        if ( Helper.isStringNullorEmpty(name) || weight <= 0 || Helper.isValidDate(releaseDate) || Helper.isListNullorEmpty(authors)
                || Helper.isObjectNull(publisher)|| price <= 0 || Helper.isStringNullorEmpty(description)|| Helper.isStringNullorEmpty(ISBN)
                || Helper.isSetNullorEmpty(genres) || Helper.isArrayNullOrEmpty(photo)){

            return null;
        }

        return new ComicBook.Builder()
                .setName(name)
                .setWeight(weight)
                .setReleaseDate(releaseDate)
                .setAuthors(authors)
                .setPublisher(publisher)
                .setPrice(price)
                .setGenres(genres)
                .setISBN(ISBN)
                .setDescription(description)
                .setQuantity(quantity)
                .setPhoto(photo)
                .build();

    }
}