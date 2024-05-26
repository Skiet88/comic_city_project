package za.ac.cput.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
@Entity
public class OrderForm {
    @Id
    private String orderId;
    private LocalDate orderDate;
    @ManyToOne(cascade = CascadeType.ALL)
    private Customer userr;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH},fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "order_comicbook",
//            joinColumns = @JoinColumn(name = "order_id"),
//            inverseJoinColumns = @JoinColumn(name = "comic_book_id")
//    )
    private List<ComicBook> comicBooks;
    private double totalAmount;

    protected OrderForm() {
    }

    private OrderForm(OrderBuilder builder) {
        this.orderId = builder.orderId;
        this.orderDate = builder.orderDate;
        this.userr = builder.userr;
        this.comicBooks = builder.comicBooks;
        this.totalAmount = builder.totalAmount;
    }

    public String getOrderId() {
        return orderId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public Customer getuserr() {
        return userr;
    }

    public List<ComicBook> getComicBooks() {
        return comicBooks;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderForm orderForm = (OrderForm) o;
        return Double.compare(orderForm.totalAmount, totalAmount) == 0 &&
                Objects.equals(orderId, orderForm.orderId) &&
                Objects.equals(orderDate, orderForm.orderDate) &&
                Objects.equals(userr, orderForm.userr) &&
                Objects.equals(comicBooks, orderForm.comicBooks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, orderDate, userr, comicBooks, totalAmount);
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", orderDate=" + orderDate +
                ", userr=" + userr +
                ", comicBooks=" + comicBooks +
                ", totalAmount=" + totalAmount +
                '}';
    }

    public static class OrderBuilder {
        private String orderId;
        private LocalDate orderDate;
        private Customer userr;
        private List<ComicBook> comicBooks;
        private double totalAmount;

        public OrderBuilder() {
        }

        public OrderBuilder setOrderId(String orderId) {
            this.orderId = orderId;
            return this;
        }

        public OrderBuilder setOrderDate(LocalDate orderDate) {
            this.orderDate = orderDate;
            return this;
        }

        public OrderBuilder setuserr(Customer userr) {
            this.userr = userr;
            return this;
        }

        public OrderBuilder setComicBooks(List<ComicBook> comicBooks) {
            this.comicBooks = comicBooks;
            return this;
        }

        public OrderBuilder setTotalAmount(double totalAmount) {
            this.totalAmount = totalAmount;
            return this;
        }

        public OrderBuilder copy(OrderForm orderForm) {
            this.orderId = orderForm.orderId;
            this.orderDate = orderForm.orderDate;
            this.userr = orderForm.userr;
            this.comicBooks = orderForm.comicBooks;
            this.totalAmount = orderForm.totalAmount;

            return this;
        }

        public OrderForm build() {
            return new OrderForm(this);
        }
    }
}
