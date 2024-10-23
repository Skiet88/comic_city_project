package za.ac.cput.service.customerOrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import za.ac.cput.domain.Author;
import za.ac.cput.domain.ComicBook;
import za.ac.cput.domain.Customer;
import za.ac.cput.domain.CustomerOrder;
import za.ac.cput.repository.ComicBookRepository;
import za.ac.cput.repository.CustomerOrderRepository;
import za.ac.cput.service.comicBookService.ComicBookService;
import za.ac.cput.service.customerService.CustomerService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerOrderService implements ICustomerOrderService {

    @Autowired
    private CustomerOrderRepository customerOrderRepository;

    private CustomerService customerService;
    private ComicBookService comicBookService;

    private ComicBookRepository comicBookRepository;

    @Autowired
    public CustomerOrderService(CustomerOrderRepository customerOrderRepository, CustomerService customerService, ComicBookService comicBookService, ComicBookRepository comicBookRepository) {

        this.customerOrderRepository = customerOrderRepository;
        this.customerService = customerService;
        this.comicBookService = comicBookService;
        this.comicBookRepository = comicBookRepository;
    }
    @Override
    public CustomerOrder create(CustomerOrder customerOrder) {
        Customer customer = customerService.create(customerOrder.getCustomer());
        List<ComicBook> comicBooks = customerOrder.getComicBooks();

        if (comicBooks != null) {
            comicBooks = comicBooks.stream()
                    .map(comicBook -> {
                        System.out.println("comicBook: " + comicBook );
                        if (comicBook.getSKU()  == null) {
                            // If comicBookID is null, save the comicBooks directly
                            return comicBookService.create(comicBook);

                        } else {

                            // If comicBookID is not null, try to find the comicBook in the repository
                            Optional<ComicBook> existingComicBook = comicBookRepository.findById(comicBook.getSKU());

                            // Return the existing comicBook if found, or save and return the new one if not found
                            return existingComicBook.orElseGet(() -> comicBookService.create(comicBook));
                        }
                    })
                    .collect(Collectors.toList());
        }

        System.out.println("Comic Books: " + comicBooks);

        customerOrder = new CustomerOrder.CustomerOrderBuilder()
                .copy(customerOrder)
                .setCustomer(customer)
                .setComicBooks(comicBooks)
                .setStatus(customerOrder.getStatus())
                .build();


        if (customerOrder != null) {
            System.out.println("Order to be Saved: " + customerOrder);
            if(customerOrder.getOrderId() ==  null ||
                    customerOrder.getOrderId() == 0){
                System.out.println("saving new customerOrder");

                customerOrder = customerOrderRepository.save(customerOrder);
                System.out.println("Saved");
                System.out.println("Saved Order" + customerOrder);
            }else{
                System.out.println("checking if existing customerOrder exists");

                Optional<CustomerOrder> existingCustomerOrder = customerOrderRepository.findById(customerOrder.getOrderId());

                if (existingCustomerOrder.isPresent()) {
                    System.out.println("found customerOrder");
                    customerOrder = existingCustomerOrder.get();
                }}
        }

        return  customerOrder;
    }

    @Override
    public CustomerOrder read(Long id) {
       return customerOrderRepository.findById(id).orElse(null);
    }

    @Override
    public CustomerOrder update(CustomerOrder customerOrder) {
        return customerOrderRepository.save(customerOrder);
    }

    @Override
    public boolean delete(Long id) {
        customerOrderRepository.deleteById(id);
        return !customerOrderRepository.existsById(id);
    }

    @Override
    public List<CustomerOrder> getall() {
        return customerOrderRepository.findAll();
    }

    public List<CustomerOrder> findCustomerOrdersByEmail(String email) {
        return customerOrderRepository.findCustomerOrdersByCustomer_ContactEmail(email);
    }
}
