package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.WishList;
@Repository
public interface WishListRepository extends JpaRepository<WishList,Long> {
    @Query("SELECT COUNT(cb) FROM WishList w JOIN w.comicBooks cb WHERE w.wishListId = :wishListId")
    int numberOfBooksOnThisWishList(@Param("wishListId") Long wishListId);

    WishList findByCustomer_Contact_Email(String email);
}

