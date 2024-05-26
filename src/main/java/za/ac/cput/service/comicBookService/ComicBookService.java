package za.ac.cput.service.comicBookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Author;
import za.ac.cput.domain.ComicBook;
import za.ac.cput.repository.AuthorRepository;
import za.ac.cput.repository.ComicBookRepository;
import za.ac.cput.service.authorService.AuthorService;
import za.ac.cput.service.publisherService.PublisherService;

import java.util.List;
@Service
public class ComicBookService implements IComicBookService{
    @Autowired
    private ComicBookRepository repo;
    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public ComicBook create(ComicBook comicBook) {

   // authorRepository.saveAll(comicBook.getAuthor());
        return repo.save(comicBook);
    }

    @Override
    public ComicBook read(String s) {
        return repo.findById(s).orElse(null);
    }

    @Override
    public ComicBook update(ComicBook comicBook) {
        return repo.save(comicBook);
    }

    @Override
    public boolean delete(String s) {
        repo.deleteById(s);

        return !repo.existsById(s);
    }

    @Override
    public List<ComicBook> getAll() {
        return repo.findAll();
    }
}
