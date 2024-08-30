package za.ac.cput.service.cartService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Cart;
import za.ac.cput.domain.ComicBook;
import za.ac.cput.domain.Customer;
import za.ac.cput.domain.CustomerOrder;
import za.ac.cput.repository.CartRepository;
import za.ac.cput.repository.ComicBookRepository;
import za.ac.cput.repository.CustomerRepository;
import za.ac.cput.service.comicBookService.ComicBookService;
import za.ac.cput.service.customerService.CustomerService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartService implements ICartService {
    private CartRepository cartRepository;
    private ComicBookRepository comicBookRepository;

    private ComicBookRepository comicBookRepository;
    private CustomerService customerService;
    private ComicBookService comicBookService;


    @Autowired
    public CartService(CartRepository cartRepository, CustomerService customerService, ComicBookService comicBookService, ComicBookRepository comicBookRepository) {
        this.cartRepository = cartRepository;

        this.customerService = customerService;
        this.comicBookRepository = comicBookRepository;
        this.comicBookService = comicBookService;


    }

    @Override
    public Cart create(Cart cart) {

        Customer customer = customerService.create(cart.getCustomer());
        List<ComicBook> comicBooks = cart.getComicBooks();

        if (comicBooks != null) {
            comicBooks = comicBooks.stream()
                    .map(comicBook -> {
                        System.out.println("comicBook: " + comicBook );
                        if (comicBook.getSKU()  == null) {
                            // If comicBookID is null, save the comicBooks directly
                            return comicBookService.create(comicBook);

                        } else {

                            // If comicBookID is not null, try to find the comicBook in the repository
                            Optional<ComicBook> existingComicBook = comicBookRepository.findById(comicBook.getSKU());

                            // Return the existing comicBook if found, or save and return the new one if not found
                            return existingComicBook.orElseGet(() -> comicBookService.create(comicBook));
                        }
                    })
                    .collect(Collectors.toList());
        }

        System.out.println("Comic Books: " + comicBooks);

        if (cart != null) {
            System.out.println("cart to be Saved: " + cart);
            if(cart.getCartId() ==  null ||
                    cart.getCartId() == 0){
                System.out.println("saving new cart");

                cart = cartRepository.save(cart);
                System.out.println("Saved");
                System.out.println("Saved cart" + cart);
            }else{
                System.out.println("checking if existing cart exists");

                Optional<Cart> existingCart = cartRepository.findById(cart.getCartId());

                if (existingCart.isPresent()) {
                    System.out.println("found cart");
                    cart = existingCart.get();
                }}
        }
        return  cart;
    }

    @Override
    public Cart read(Long cartId) {
        return cartRepository.findById(cartId).orElse(null);
    }

    @Override
    public Cart update(Cart cart) {
        System.out.println(cart);
        comicBookRepository.saveAll(cart.getComicBooks());
        return cartRepository.save(cart);
    }

    @Override
    public boolean delete(Long cartId) {
        cartRepository.deleteById(cartId);
        return !cartRepository.existsById(cartId);
    }

    @Override
    public List<Cart> getall() {
        return cartRepository.findAll();
    }

    public double getCartTotalPrice(Long cartId) {
        return cartRepository.calculateCartTotalPrice(cartId);
    }

    public int quantity(Long cartId) {
        return cartRepository.numberOfBooksOnThisCart(cartId);
    }

    public Cart getCartByCustomerEmail(String email) {
        return cartRepository.findCartByCustomer_ContactEmail(email);
    }
}
