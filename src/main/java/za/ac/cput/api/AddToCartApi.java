package za.ac.cput.api;

import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.cput.domain.Cart;
import za.ac.cput.domain.ComicBook;
import za.ac.cput.service.cartService.CartService;
import za.ac.cput.service.comicBookService.ComicBookService;

import java.time.LocalDate;

@Component
public class AddToCartApi {
    private final ComicBookService comicBookService;
    private final CartService cartService;

    @Autowired
    public AddToCartApi(CartService cartService, ComicBookService comicBookService) {
        this.cartService = cartService;
        this.comicBookService = comicBookService;
    }

    public Cart addComicBookToCart(Long cartId, Long sku) {
        // Retrieve the cart and comic book
        Cart cart = cartService.read(cartId);
        System.out.println("Before adding to cart operation"+cart);
        ComicBook comicBook = comicBookService.read(sku);
        System.out.println("Before adding to cart operation"+comicBook);
        Cart newCart = null;

        if (cart != null && comicBook != null) {
            boolean updated = false;

            for (ComicBook comicBook1 : cart.getComicBooks()) {
                if (comicBook1.getSKU().equals(sku)) {
                    // Update the existing comic book
                    int newQuantity = comicBook1.getQuantity() + 1;
                    ComicBook bookUpdated = new ComicBook.Builder()
                            .copy(comicBook1)
                            .setQuantity(newQuantity)
                            .build();

                    cart.getComicBooks().remove(comicBook1);
                    cart.getComicBooks().add(bookUpdated);
                    updated = true;
                    break;
                }
            }

            if (!updated) {
                // Comic book not found in the cart, add a new entry
                ComicBook newComicBook = new ComicBook.Builder()
                        .copy(comicBook)
                        .setQuantity(1)
                        .build();
                cart.getComicBooks().add(newComicBook);
                System.out.println("Not found added to cart");
                System.out.println(cart);
            }

            // Create a new Cart instance with updated date
            Cart updatedCart = new Cart.Builder()
                    .copy(cart)
                    .setUpdatedDate(LocalDate.now())
                    .build();


            System.out.println("Hey"+newCart);
            return cartService.update(updatedCart);
        }


    return null;}
}
