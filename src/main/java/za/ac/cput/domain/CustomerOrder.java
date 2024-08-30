package za.ac.cput.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
public class CustomerOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private LocalDate orderDate;



    @ManyToMany(cascade=CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(
            name = "order_comicbook",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "comic_book_id")
    )
    private List<ComicBook> comicBooks;


    private double totalAmount;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;



    protected CustomerOrder() {
    }

    private CustomerOrder(CustomerOrderBuilder builder) {
        this.orderId = builder.orderId;
        this.orderDate = builder.orderDate;

        this.comicBooks = builder.comicBooks;
        this.totalAmount = builder.totalAmount;
        this.customer = builder.customer;


    }



    public Long getOrderId() {
        return orderId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }


    public List<ComicBook> getComicBooks() {
        return comicBooks;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
    public Customer getCustomer() {
        return customer;
    }


    @Override
    public String toString() {
        return "CustomerOrder{" +
                "orderId='" + orderId + '\'' +
                ", orderDate=" + orderDate +
                ", comicBooks=" + comicBooks +
                ", totalAmount=" + totalAmount +
                ", customer=" + customer +
                '}';
    }

    public static class CustomerOrderBuilder {
        private Long orderId;
        private LocalDate orderDate;

        private List<ComicBook> comicBooks;
        private double totalAmount;
        private Customer customer;


        public CustomerOrderBuilder() {
        }

        public CustomerOrderBuilder setCustomer(Customer customer) {
            this.customer = customer;
            return this;
        }

        public CustomerOrderBuilder setOrderId(Long orderId) {
            this.orderId = orderId;
            return this;
        }

        public CustomerOrderBuilder setOrderDate(LocalDate orderDate) {
            this.orderDate = orderDate;
            return this;
        }



        public CustomerOrderBuilder setComicBooks(List<ComicBook> comicBooks) {
            this.comicBooks = comicBooks;
            return this;
        }

        public CustomerOrderBuilder setTotalAmount(double totalAmount) {
            this.totalAmount = totalAmount;
            return this;
        }

        public CustomerOrderBuilder copy(CustomerOrder customerOrder) {
            this.orderId = customerOrder.orderId;
            this.orderDate = customerOrder.orderDate;

            this.comicBooks = customerOrder.comicBooks;
            this.totalAmount = customerOrder.totalAmount;


            return this;
        }

        public CustomerOrder build() {
            return new CustomerOrder(this);
        }
    }
}
