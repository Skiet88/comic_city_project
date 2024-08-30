package za.ac.cput.service.publisherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Author;
import za.ac.cput.domain.Publisher;
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
    public Publisher create(Publisher publisher) {

        if (publisher != null) {
            System.out.println("checking if existing publisher exists");
            Optional<Publisher> existingPublisher = repository.findById(publisher.getPublisherId());
            if (existingPublisher.isPresent()) {
                System.out.println("found publisher");
                publisher = existingPublisher.get();
            } else {
                System.out.println("saving new publisher");
                publisher = repository.save(publisher);
                System.out.println("Saved");
            }
        }
        return publisher;
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
}
