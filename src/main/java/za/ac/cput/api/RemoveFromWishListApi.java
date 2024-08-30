package za.ac.cput.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.cput.domain.Cart;
import za.ac.cput.domain.ComicBook;
import za.ac.cput.domain.WishList;
import za.ac.cput.service.cartService.CartService;
import za.ac.cput.service.comicBookService.ComicBookService;
import za.ac.cput.service.wishListService.WishListService;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

@Component
public class RemoveFromWishListApi {
    private final ComicBookService comicBookService;
    private final WishListService wishListService;

    @Autowired
    public RemoveFromWishListApi(WishListService wishListService, ComicBookService comicBookService) {
        this.wishListService=wishListService;
        this.comicBookService = comicBookService;
    }

    public WishList removeBookFromWishList(Long wishListId, Long sku) {
        // Retrieve the wishList
        WishList wishList = wishListService.read(wishListId);
        if (wishList == null) {
            // Handle cart not found case
            System.out.println("WishList not found with ID: " + wishListId);
            return null;
        }

        System.out.println("Before removing from wishList operation: " + wishList);

        // Flag to check if the comic book was found and removed
        boolean updated = false;

        // Use Iterator to safely remove items from the list
        Iterator<ComicBook> iterator = wishList.getComicBooks().iterator();
        while (iterator.hasNext()) {
            ComicBook comicBook1 = iterator.next();
            if (comicBook1.getSKU().equals(sku)) {
                System.out.println("Removing comic book with SKU: " + comicBook1.getSKU());
                iterator.remove(); // Remove the comic book from the cart
                updated = true;
                break;
            }
        }

        if (!updated) {
            // Handle case where comic book was not found in the cart
            System.out.println("Comic book with SKU " + sku + " not found in cart.");
            return wishList; // Return the cart as is
        }

        // Create a new Cart instance with updated date
        WishList updatedWishList = new WishList.Builder()
                .copy(wishList)
                .setUpdatedDate(LocalDate.now())
                .build();

        // Update the cart in the service
        return wishListService.update(updatedWishList);
    }
}
