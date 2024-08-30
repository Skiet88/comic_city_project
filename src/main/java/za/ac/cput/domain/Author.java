package za.ac.cput.domain;
// Mlungisi L. Mbuyazi
// 221164014
// https://github.com/Skiet88/comic__city_project

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long authorID;
     @Embedded
    private Name name;

    @ManyToMany(mappedBy = "authors", fetch = FetchType.EAGER)
    private List<ComicBook> comicBooks ;

    protected Author() {
    }

    private Author(AuthorBuilder a) {
        this.authorID = a.authorID;
        this.name = a.name;
    }

    public Long getAuthorID() {
        return authorID;
    }

    public Name getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Domain.Author{" +
                "authorID=" + authorID +
                ", name=" + name +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return authorID == author.authorID && Objects.equals(name, author.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorID, name);
    }

    public static class AuthorBuilder{
        private long authorID;
        private Name name;

        public AuthorBuilder() {
        }

        public AuthorBuilder setAuthorID(Long authorID) {
            this.authorID = authorID;
            return this;
        }

        public AuthorBuilder setName(Name name) {
            this.name = name;
            return this;
        }
        public AuthorBuilder copy(Author a) {
            this.authorID = a.authorID;
            this.name = a.name;

            return this;
        }

        public Author build() {
            return new Author(this);
        }
    }

}
