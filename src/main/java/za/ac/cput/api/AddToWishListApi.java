package za.ac.cput.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.cput.domain.ComicBook;
import za.ac.cput.domain.WishList;

import za.ac.cput.service.comicBookService.ComicBookService;
import za.ac.cput.service.wishListService.WishListService;


import java.time.LocalDate;

@Component
public class AddToWishListApi {
    private final ComicBookService comicBookService;
    private final WishListService wishListService;

    @Autowired
    public AddToWishListApi(WishListService wishListService, ComicBookService comicBookService) {
        this.wishListService = wishListService;
        this.comicBookService = comicBookService;
    }

    public WishList addComicBookToWishList(Long wishlistId, Long sku) {
        // Retrieve the wishlist and comic book
        WishList wishlist = wishListService.read(wishlistId);
        System.out.println("Before adding to wishlist operation: " + wishlist);
        ComicBook comicBook = comicBookService.read(sku);
        System.out.println("Before adding to wishlist operation: " + comicBook);

        if (wishlist != null && comicBook != null) {
            // Check if the comic book is already in the wishlist
            for (ComicBook comicBookInWishlist : wishlist.getComicBooks()) {
                if (comicBookInWishlist.getSKU().equals(sku)) {
                    System.out.println("Comic book already in wishlist");
                    return wishlist;
                }
            }

            // Comic book not found in the wishlist, add a new entry
            ComicBook newComicBook = new ComicBook.Builder()
                    .copy(comicBook)
                    .setQuantity(1) // You might not need quantity for a wishlist
                    .build();
            wishlist.getComicBooks().add(newComicBook);
            System.out.println("Comic book added to wishlist: " + newComicBook);

            // Update the wishlist with the new comic book and updated date
            WishList updatedWishlist = new WishList.Builder()
                    .copy(wishlist)
                    .setUpdatedDate(LocalDate.now())
                    .build();

            return wishListService.update(updatedWishlist);
        }

        return null;
    }
}
