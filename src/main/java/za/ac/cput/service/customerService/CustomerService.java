package za.ac.cput.service.customerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.*;
import za.ac.cput.repository.CustomerRepository;
import za.ac.cput.repository.RoleRepository;
import za.ac.cput.service.contactService.ContactService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService implements ICustomerService{
    @Autowired
    private CustomerRepository repository;
    @Autowired
    private ContactService contactService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public Customer create(Customer customer) {

        String encodedPassword = passwordEncoder.encode(customer.getPassword());
        Role adminRole = roleRepository.findByName("ROLE_CUSTOMER")
                .orElseThrow(() -> new RuntimeException("Role not found: ROLE_CUSTOMER"));

        Customer customer2 = new Customer.CustomerBuilder()
                .copy(customer)
                .setPassword(encodedPassword)
                .setRoles(Collections.singleton(adminRole))
                .build();

        return repository.save(customer2);
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

    public Customer authenticationByEmail(String email, String password) {
        return repository.findByContactEmailAndPassword(email, password);
    }
    public  Customer getCustomerByEmail(String email){
        System.out.println( "enterd wiyth: " + email);
        return repository.findByContact_Email(email);
    }
}
