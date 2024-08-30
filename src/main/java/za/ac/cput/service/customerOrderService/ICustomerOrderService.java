package za.ac.cput.service.customerOrderService;

import za.ac.cput.domain.CustomerOrder;
import za.ac.cput.service.IService;

import java.util.List;

public interface ICustomerOrderService extends IService<CustomerOrder, Long> {
    List<CustomerOrder> getall();
}
