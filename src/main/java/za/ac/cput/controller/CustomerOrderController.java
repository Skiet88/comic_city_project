package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.CustomerOrder;
import za.ac.cput.domain.OrderStatus;
import za.ac.cput.service.customerOrderService.CustomerOrderService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/CustomerOrder")
public class CustomerOrderController {

    @Autowired
    private CustomerOrderService customerOrderService;

    @PostMapping("/create")
    public CustomerOrder create(@RequestBody CustomerOrder customerOrder) {
        System.out.println("Received CustomerOrder: " + customerOrder);
        LocalDate orderDate = LocalDate.now();
        OrderStatus status = OrderStatus.PROCESSING;

        CustomerOrder newOrder = new CustomerOrder.CustomerOrderBuilder()
                .setOrderDate(orderDate)
                .setComicBooks(customerOrder.getComicBooks())
                .setCustomer(customerOrder.getCustomer())
                .setTotalAmount(customerOrder.getTotalAmount())
                .setStatus(status)
                .build();
        return customerOrderService.create(newOrder);
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
        System.out.println("Entered get all");
        return customerOrderService.getall();
    }
    @GetMapping("/getCustomerOrders/{email}")
    public List<CustomerOrder> getCustomerOrders(@PathVariable String email) {
        return customerOrderService.findCustomerOrdersByEmail(email);
    }
}
