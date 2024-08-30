package za.ac.cput.repository;
//
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {

    @Query("SELECT SUM(cb.price) FROM Cart c JOIN c.comicBooks cb WHERE c.cartId = :cartId")
    Double calculateCartTotalPrice(@Param("cartId") Long cartId);

    @Query("SELECT COUNT(cb) FROM Cart c JOIN c.comicBooks cb WHERE c.cartId = :cartId")
    int numberOfBooksOnThisCart(@Param("cartId") Long cartId);

    Cart findCartByCustomer_ContactEmail(String email);
}
