package za.ac.cput.service.comicBookService;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Author;
import za.ac.cput.domain.ComicBook;

import za.ac.cput.domain.Publisher;
import za.ac.cput.repository.AuthorRepository;
import za.ac.cput.repository.ComicBookRepository;
import za.ac.cput.repository.PublisherRepository;
import za.ac.cput.service.authorService.AuthorService;
import za.ac.cput.service.publisherService.PublisherService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ComicBookService implements IComicBookService{
    @Autowired
    private ComicBookRepository repo;  // Use 'repo' as the name for ComicBookRepository

    @Autowired
    private PublisherService publisherService;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    public ComicBookService(ComicBookRepository repository) {
        this.repo = repository;
    }


    @Transactional
    public ComicBook create(ComicBook comicBook) {
        // Handle Publisher
        System.out.println("Entered Create Comic Book");
        Publisher publisher =  publisherService.create(comicBook.getPublisher());

        System.out.println("Publisher: " + publisher);
        // Handle Authors
        List<Author> authors = comicBook.getAuthors();

        if (authors != null) {
            authors = authors.stream()
                    .map(author -> {
                        System.out.println("Author: " + author );
                        if (author.getAuthorID()  == null) {
                            // If authorID is null, save the author directly
                            return authorRepository.save(author);

                        } else {

                            // If authorID is not null, try to find the author in the repository
                            Optional<Author> existingAuthor = authorRepository.findById(author.getAuthorID());
                            // Return the existing author if found, or save and return the new one if not found
                            return existingAuthor.orElseGet(() -> authorRepository.save(author));
                        }
                    })
                    .collect(Collectors.toList());
        }


        System.out.println("Authors: " + authors);

        ComicBook updatedComicBook = new ComicBook.Builder()
                .copy(comicBook)
                .setPublisher(publisher)
                .setAuthors(authors)
                .build();

        return repo.save(updatedComicBook);  // Use 'repo' to save the ComicBook
    }
    @Override
    public ComicBook read(Long s) {
        return repo.findById(s).orElse(null);
    }

    @Transactional
    public ComicBook update(ComicBook comicBook) {
        // Handle Publisher
        Publisher publisher =  publisherService.create(comicBook.getPublisher());
        System.out.println("Publisher: " + publisher);

        // Handle Authors
        List<Author> authors = comicBook.getAuthors();
        if (authors != null) {

            authors = authors.stream()
                    .map(author -> {
                        Optional<Author> existingAuthor = authorRepository.findById(author.getAuthorID());
                        return existingAuthor.orElseGet(() -> authorRepository.save(author));
                    })
                    .collect(Collectors.toList());
        }


        // Create updated ComicBook with the managed entities
        ComicBook updatedComicBook = new ComicBook.Builder()
                .copy(comicBook)
                .setPublisher(publisher)
                .setAuthors(authors)
                .build();

        // Save updated ComicBook
        return repo.save(updatedComicBook);
    }

    @Override
    public boolean delete(Long s) {
        repo.deleteById(s);
        return !repo.existsById(s);
    }

    @Override
    public List<ComicBook> getAll() {
        return repo.findAll();
    }
}
