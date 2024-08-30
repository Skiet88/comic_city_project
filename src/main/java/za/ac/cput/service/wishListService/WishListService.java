package za.ac.cput.service.wishListService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Cart;
import za.ac.cput.domain.ComicBook;
import za.ac.cput.domain.Customer;
import za.ac.cput.domain.WishList;
import za.ac.cput.repository.ComicBookRepository;
import za.ac.cput.repository.WishListRepository;
import za.ac.cput.service.comicBookService.ComicBookService;
import za.ac.cput.service.customerService.CustomerService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WishListService implements IWishListService {
    private WishListRepository wishListRepository;
    private CustomerService customerService;
    private ComicBookService comicBookService;
    private ComicBookRepository comicBookRepository;

    @Autowired
    public WishListService(WishListRepository wishListRepository, CustomerService customerService, ComicBookService comicBookService, ComicBookRepository comicBookRepository ) {
        this.wishListRepository = wishListRepository;
        this.customerService = customerService;
        this.comicBookRepository = comicBookRepository;
        this.comicBookService = comicBookService;
    }

    @Override
    public WishList create(WishList wishList) {
        Customer customer = customerService.create(wishList.getCustomer());
        List<ComicBook> comicBooks = wishList.getComicBooks();

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

        if (wishList != null) {
            System.out.println("wishList to be Saved: " + wishList);
            if(wishList.getWishListId() ==  null ||
                    wishList.getWishListId() == 0){
                System.out.println("saving new wishList");

                wishList = wishListRepository.save(wishList);
                System.out.println("Saved");
                System.out.println("Saved wishList" + wishList);
            }else{
                System.out.println("checking if existing wishList exists");

                Optional<WishList> existingWishList = wishListRepository.findById(wishList.getWishListId());

                if (existingWishList.isPresent()) {
                    System.out.println("found wishList");
                    wishList = existingWishList.get();
                }}
        }
        return  wishList;
    }

    @Override
    public WishList read(Long wishListId) {
        return wishListRepository.findById(wishListId).orElse(null);
    }

    @Override
    public WishList update(WishList wishList) {
        return wishListRepository.save(wishList);
    }

    @Override
    public boolean delete(Long wishListId) {
        wishListRepository.deleteById(wishListId);
        return !wishListRepository.existsById(wishListId);
    }

    @Override
    public List<WishList> getall() {
        return wishListRepository.findAll();
    }
    public int calculateQuantity(Long wishListId){
        return wishListRepository.numberOfBooksOnThisWishList(wishListId);
    }
}
