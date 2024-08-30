package za.ac.cput.factory;

import za.ac.cput.domain.ComicBook;
import za.ac.cput.domain.Customer;
import za.ac.cput.domain.CustomerOrder;
import za.ac.cput.util.Helper;

import java.time.LocalDate;
import java.util.List;

public class CustomerOrderFactory {
    public static CustomerOrder buildCustomerOrder( LocalDate orderDate, List<ComicBook> comicBooks, Customer customer ,double totalAmount) {
        if (Helper.isValidDate(orderDate)
    || comicBooks == null || customer == null|| comicBooks.isEmpty() || totalAmount <= 0.0 || Helper.isValidDate(orderDate)) {
            return null;
        }

        return new CustomerOrder.CustomerOrderBuilder()
                .setOrderDate(orderDate)
                .setComicBooks(comicBooks)
                .setTotalAmount(totalAmount)
                .setCustomer(customer)
                .build();
    }
}

