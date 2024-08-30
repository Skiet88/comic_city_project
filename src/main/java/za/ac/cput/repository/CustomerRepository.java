package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Customer;

@Repository
public interface
CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByContactEmailAndPassword(@Param("email") String email, @Param("password") String password);
    Customer findByContact_Email(@Param("email")String email);
}
