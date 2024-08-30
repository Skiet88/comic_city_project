package za.ac.cput.service.customerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Author;
import za.ac.cput.domain.Contact;
import za.ac.cput.domain.Customer;
import za.ac.cput.repository.CustomerRepository;
import za.ac.cput.service.contactService.ContactService;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService implements ICustomerService{
    @Autowired
    private CustomerRepository repository;
    @Autowired
    private ContactService contactService;


    @Override
    public Customer create(Customer customer) {

        Contact contact = contactService.create(customer.getContact());
        System.out.println("Saved Contact: " + contact);
        System.out.println("In create with customer: " + customer);
        if(customer.getCustomerId() ==  null ||
                customer.getCustomerId() == 0){
            System.out.println("saving new customer");

            customer = repository.save(customer);
            System.out.println("Saved");
            System.out.println("Saved customer" + customer);
        }else{
            System.out.println("checking if existing customer exists");

            Optional<Customer> existingProperty = repository.findById(customer.getCustomerId());

            if (existingProperty.isPresent()) {
                System.out.println("found customer");
                customer = existingProperty.get();
            }}

        return  customer;
    }

    @Override
    public Customer read(Long aLong) {
        return repository.findById(aLong).orElse(null);
    }

    @Override
    public Customer update(Customer customer) {
        System.out.println("Entered update: " + customer);
        return repository.save(customer);
    }

    @Override
    public boolean delete(Long aLong) {
        repository.deleteById(aLong);
        return !repository.existsById(aLong);
    }


    @Override
    public List<Customer> getall() {
        return repository.findAll();
    }
}
