package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Cart;
import za.ac.cput.service.cartService.CartService;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    private CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }


    @PostMapping("/create")
    public Cart create(@RequestBody Cart cart) {
        return cartService.create(cart);
    }

    @GetMapping("/read/{cartId}")
    public Cart read(@PathVariable("cartId") Long cartId) {
        System.out.println("Entered read method");
        return cartService.read(cartId);
    }

    @PostMapping("/update")
    public Cart update(@RequestBody Cart cart) {
        return cartService.update(cart);
    }

    @DeleteMapping("/delete/{cartId}")
    public boolean delete(@PathVariable("cartId") Long cartId) {
        return cartService.delete(cartId);
    }

    @GetMapping("/getall")
    public List<Cart> getall() {
        return cartService.getall();
    }

    @GetMapping("/totalPrice/{cartId}")

    public double getCartTotalPrice(@PathVariable("cartId") Long cartId) {
        return cartService.getCartTotalPrice(cartId);
    }

    @GetMapping("/quantity/{cartId}")
    public int quantity(@PathVariable("cartId") Long cartId) {
        return cartService.quantity(cartId);
    }
}


