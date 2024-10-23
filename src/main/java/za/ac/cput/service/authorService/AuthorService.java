package za.ac.cput.service.authorService;
// Mlungisi L. Mbuyazi
// 221164014
// https://github.com/Skiet88/comic_city_project
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Address;
import za.ac.cput.domain.Author;
import za.ac.cput.repository.AuthorRepository;
import za.ac.cput.repository.CartRepository;
import za.ac.cput.repository.ComicBookRepository;
import za.ac.cput.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class AuthorService implements IAuthorService{

    private AuthorRepository repo;
    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.repo = authorRepository;
    }

    @Override
    public Author create(Author author) {
        return repo.save(author);
    }

    @Override
    public Author read(Long l) {
        return repo.findById(l).orElse(null);
    }

    @Override
    public Author update(Author author) {
        return repo.save(author);
    }

    @Override
    public boolean delete(Long l) {
       repo.deleteById(l);
       return !repo.existsById(l);
    }

    @Override
    public List<Author> getAll() {
        return repo.findAll();
    }

    public List<Author> searchByName(String name) {

        List<Author> firstNameResults = repo.findByName_FirstNameContainingIgnoreCase(name);
        List<Author> lastNameResults = repo.findByName_LastNameContainingIgnoreCase(name);


        return Stream.concat(firstNameResults.stream(), lastNameResults.stream())
                .distinct()
                .collect(Collectors.toList());
    }
}
