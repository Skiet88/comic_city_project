package za.ac.cput.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * Mpumzi Mbula
 * 219053324
 * 17/05/2024
 *
 */
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;
    @OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private Customer customer;
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
    @JoinTable(
            name = "cart_comicbook",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "comic_book_id")
    )
    private List<ComicBook> comicBooks;

    private LocalDate createdDate;
    private LocalDate updatedDate;

    public Cart() {
    }
    protected Cart(Builder builder){
       this.cartId=builder.cartId;
       this.customer=builder.customer;
       this.comicBooks=builder.comicBooks;
       this.createdDate=builder.createdDate;
       this.updatedDate=builder.updatedDate;

    }

    public Long getCartId() {
        return cartId;
    }

    public List<ComicBook> getComicBooks() {
        return comicBooks;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cart cart)) return false;
        return Objects.equals(cartId, cart.cartId) && Objects.equals(customer, cart.customer) && Objects.equals(comicBooks, cart.comicBooks) && Objects.equals(createdDate, cart.createdDate) && Objects.equals(updatedDate, cart.updatedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cartId, customer, comicBooks, createdDate, updatedDate);
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartId=" + cartId +
                ", customer=" + customer +
                ", comicBooks=" + comicBooks +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }

    public static class Builder{

        private Long cartId;
        private Customer customer;
        private  List<ComicBook> comicBooks;
        private LocalDate createdDate;
        private LocalDate updatedDate;


        public Builder() {
        }

        public Builder setCartId(Long cartId) {
            this.cartId = cartId;
            return this;
        }

        public Builder setCustomer(Customer customer) {
            this.customer = customer;
            return this;
        }

        public Builder setComicBooks(List<ComicBook> comicBooks) {
            this.comicBooks = comicBooks;
            return this;
        }

        public Builder setCreatedDate(LocalDate createdDate) {
            this.createdDate = createdDate;
            return this;
        }

        public Builder setUpdatedDate(LocalDate updatedDate) {
            this.updatedDate = updatedDate;
            return this;
        }
        public Builder copy(Cart cart){
            this.cartId=cart.cartId;
            this.customer=cart.customer;
            this.comicBooks=cart.comicBooks;
            this.createdDate=cart.createdDate;
            this.updatedDate=cart.updatedDate;
            return this;
        }

        public Cart build(){
            return new Cart(this);
        }
    }


}
