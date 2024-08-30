package za.ac.cput.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;


import java.util.List;
import java.util.Objects;
@Entity
public class Customer extends User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    protected Customer() {
    }

    private Customer(CustomerBuilder c) {
        this.customerId = c.customerId;
        name = c.name;
        password = c.password;
        contact = c.contact;
    }


    public Long getCustomerId() {
        return customerId;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", password='" + password + '\'' +
                ", name=" + name +
                ", contact=" + contact +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer customer)) return false;
        return Objects.equals(customerId, customer.customerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId);
    }

    public static class CustomerBuilder{

        private long customerId;
        private Name name;
        private String password;
        private Contact contact;

        public CustomerBuilder() {
        }


        public CustomerBuilder setCustomerId(long customerId) {
            this.customerId = customerId;
            return this;
        }

        public CustomerBuilder setName(Name name) {
            this.name = name;
            return this;
        }

        public CustomerBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public CustomerBuilder setContact(Contact contact) {
            this.contact = contact;
            return this;
        }

        public CustomerBuilder copy(Customer c) {
            this.customerId = c.customerId;
            this.name = c.name;
            this.password = c.password;
            contact = c.contact;
            return this;
        }

        public Customer build(){
            return new Customer(this);
        }
    }
}
