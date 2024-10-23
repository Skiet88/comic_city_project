package za.ac.cput.service.publisherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Author;
import za.ac.cput.domain.Publisher;
import za.ac.cput.domain.WishList;
import za.ac.cput.factory.PublisherFactory;
import za.ac.cput.repository.AuthorRepository;
import za.ac.cput.repository.PublisherRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PublisherService implements IPublisherService {

    private PublisherRepository repository;


    @Autowired
    public PublisherService(PublisherRepository repository) {
        this.repository = repository;
    }

    @Override
    public Publisher create(Publisher p) {
        Publisher publisher = PublisherFactory.buildPublisher(
                p.getName(),
                p.getYearFounded()
        );
        return repository.save(publisher);
    }

    @Override
    public Publisher read(Long aLong) {
        return repository.findById(aLong).orElse(null);
    }


    @Override
    public Publisher update(Publisher publisher) {
        return repository.save(publisher);
    }

    @Override
    public boolean delete(Long aLong) {
        repository.deleteById(aLong);
        return !repository.existsById(aLong);
    }

    @Override
    public List<Publisher> getall() {
        return repository.findAll();
    }

    public List<Publisher> findByName(String name) {
        return repository.findByNameContainingIgnoreCase(name);
    }
}
