package za.ac.cput.service.reviewService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import za.ac.cput.domain.ComicBook;
import za.ac.cput.domain.Customer;
import za.ac.cput.domain.Review;
import za.ac.cput.repository.ReviewRepository;
import za.ac.cput.service.comicBookService.ComicBookService;
import za.ac.cput.service.customerService.CustomerService;

import java.util.List;

@Service
public class ReviewService implements IReviewService {
    @Autowired
    private ReviewRepository repo;
    @Autowired
    private ComicBookService comicBookService;

    @Autowired
    private CustomerService customerService;

    @Transactional
    public Review create(Review review) {

        Customer customer = review.getCustomer();
        if (customer != null) {
            customer = customerService.create(customer);
        }
        // Handle ComicBook
        ComicBook comicBook = review.getComicBook();
        if (comicBook != null) {
            comicBook = comicBookService.create(comicBook);
        }

        review.setComicBook(comicBook);

        return repo.save(review);
    }

    @Override
    public Review read(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Review update(Review review) {
        // Handle ComicBook
        ComicBook comicBook = review.getComicBook();
        if (comicBook != null) {
            comicBook = comicBookService.create(comicBook);
        }

        review.setComicBook(comicBook);return repo.save(review);
    }

    @Override
    public boolean delete(Long id) {
        repo.deleteById(id);
        return !repo.existsById(id);
    }

    @Override
    public List<Review> getAll() {
        return repo.findAll();
    }

}
