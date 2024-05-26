package za.ac.cput.service.OrderService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.OrderForm;
import za.ac.cput.repository.OrderRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<OrderForm> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<OrderForm> getOrderById(String id) {
        return orderRepository.findById(id);
    }

    public OrderForm saveOrder(OrderForm orderForm) {
        return orderRepository.save(orderForm);
    }

    public void deleteOrderById(String id) {
        orderRepository.deleteById(id);
    }
}

