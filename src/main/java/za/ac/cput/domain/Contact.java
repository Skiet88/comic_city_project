package za.ac.cput.domain;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Contact_type" , discriminatorType = DiscriminatorType.STRING)
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public class Contact {
    @Id
    protected  String email;
    protected  String mobile;

    public Contact() {
    }
    protected Contact(ContactBuilder builder){
        this.email=builder.email;
        this.mobile=builder.mobile;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contact contact)) return false;
        return Objects.equals(email, contact.email) && Objects.equals(mobile, contact.mobile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, mobile);
    }

    @Override
    public String toString() {
        return "Contact{" +
                "email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                '}';
    }

    public static class ContactBuilder{
        protected  String email;
        protected  String mobile;

        public ContactBuilder() {
        }

        public ContactBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public ContactBuilder setMobile(String mobile) {
            this.mobile = mobile;
            return this;
        }
        public ContactBuilder copy(Contact contact){
            this.email= contact.email;
            this.mobile= contact.mobile;
            return this;
        }
        public Contact build(){
            return new Contact(this);
        }
    }
}
