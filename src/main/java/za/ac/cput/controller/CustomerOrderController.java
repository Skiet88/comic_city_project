package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.CustomerOrder;
import za.ac.cput.service.customerOrderService.CustomerOrderService;

import java.util.List;

@RestController
@RequestMapping("/CustomerOrder")
public class CustomerOrderController {

    @Autowired
    private CustomerOrderService customerOrderService;

    @PostMapping("/create")
    public CustomerOrder create(@RequestBody CustomerOrder customerOrder) {
        return customerOrderService.create(customerOrder);
    }

    @GetMapping("/read/{customerOrderId}")
    public CustomerOrder read(@PathVariable("customerOrderId") Long customerOrderId) {
        return customerOrderService.read(customerOrderId);
    }

    @PostMapping("/update")
    public CustomerOrder update(@RequestBody CustomerOrder customerOrder) {
        System.out.println("Entered update");
        return customerOrderService.update(customerOrder);
    }

    @DeleteMapping("/delete/{customerId}")
    public void delete(@PathVariable Long customerId) {
        customerOrderService.delete(customerId);
    }

    @GetMapping("/getAll")
    public List<CustomerOrder> findAll() {
        return customerOrderService.getall();
    }
}
