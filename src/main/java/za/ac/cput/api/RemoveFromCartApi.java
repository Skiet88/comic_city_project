package za.ac.cput.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.cput.domain.Cart;
import za.ac.cput.domain.ComicBook;
import za.ac.cput.service.cartService.CartService;
import za.ac.cput.service.comicBookService.ComicBookService;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

@Component
public class RemoveFromCartApi {
    private final ComicBookService comicBookService;
    private final CartService cartService;

    @Autowired
    public RemoveFromCartApi(CartService cartService, ComicBookService comicBookService) {
        this.cartService = cartService;
        this.comicBookService = comicBookService;
    }

    public Cart removeBookFromCart(Long cartId, Long sku) {
        // Retrieve the cart
        Cart cart = cartService.read(cartId);
        if (cart == null) {
            // Handle cart not found case
            System.out.println("Cart not found with ID: " + cartId);
            return null;
        }

        System.out.println("Before removing from cart operation: " + cart);

        // Flag to check if the comic book was found and removed
        boolean updated = false;

        // Use Iterator to safely remove items from the list
        Iterator<ComicBook> iterator = cart.getComicBooks().iterator();
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
            return cart; // Return the cart as is
        }

        // Create a new Cart instance with updated date
        Cart updatedCart = new Cart.Builder()
                .copy(cart)
                .setUpdatedDate(LocalDate.now())
                .build();

        // Update the cart in the service
        return cartService.update(updatedCart);
    }
}
