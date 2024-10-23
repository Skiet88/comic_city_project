package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Customer;
import za.ac.cput.service.customerService.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/Customer")
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerController {
    @Autowired
    private CustomerService service;

    @PostMapping("/create")
    public ResponseEntity<Customer> create(@RequestBody Customer customer) {
        System.out.println("Entered Customer");

        System.out.println("Customer: " + customer);

        Customer savedCustomer = service.create(customer);
        System.out.println("Saved Customer: " + savedCustomer);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCustomer);
    }
    @GetMapping("/read/{customerId}")
    public Customer read(@PathVariable long customerId){
        return service.read(customerId);
    }

    @PostMapping("/update")
    public Customer update(@RequestBody Customer customer){
        return service.update(customer);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable long id){
        service.delete(id);
    }

    @GetMapping("/getall")
    public List<Customer> getall(){
        return service.getall();
    }

    @GetMapping("/login/{email}/{password}")
    public ResponseEntity<String> login(@PathVariable("email") String email, @PathVariable("password") String password) {

        if (email ==null || email.isEmpty()) {
            return new ResponseEntity<>("email is null" + email, HttpStatus.BAD_REQUEST);
        }

        Customer authenticatedCustomer = service.authenticationByEmail(email, password);

        if (authenticatedCustomer != null) {
            return new ResponseEntity<>("Login successful", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid email or password", HttpStatus.UNAUTHORIZED);
        }
    }
    @GetMapping("/getByEmail/{email}")
    public Customer getByEmail(@PathVariable("email") String email){
        System.out.println( "enterd cus controller: " + email);
        return service.getCustomerByEmail(email);
    }

}
