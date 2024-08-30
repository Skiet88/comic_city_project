package za.ac.cput.factory;

import  za.ac.cput.domain.Cart;
import za.ac.cput.domain.ComicBook;

import za.ac.cput.domain.Customer;
import za.ac.cput.util.Helper;

import java.time.LocalDate;
import java.util.List;

/**
 * Mpumzi Mbula
 * 219053324
 * 17/05/2024
 */
public class CartFactory {
    public static Cart buildCart(Long cartId, Customer customer, List<ComicBook> comicBooks, LocalDate createdDate, LocalDate updateDate) {
if (cartId <= 0 ||
               Helper.isObjectNull(customer)||
               Helper.isListNullorEmpty(comicBooks) ||
               Helper.isAfter(createdDate, LocalDate.now())||
               Helper.isAfter(updateDate, LocalDate.now())
        )

        {
   return null;
        }

        return new Cart.Builder().setCartId(cartId)
                .setCustomer(customer)
                .setComicBooks(comicBooks)
                .setCreatedDate(createdDate)
                .setUpdatedDate(updateDate)
                .build();
    }
    public static Cart buildCart( Customer customer, List<ComicBook> comicBooks, LocalDate createdDate, LocalDate updateDate) {
        if (
                Helper.isObjectNull(customer)||
                Helper.isListNullorEmpty(comicBooks) ||
                Helper.isAfter(createdDate, LocalDate.now())||
                Helper.isAfter(updateDate, LocalDate.now())
        )

        {
            return null;
        }

        return new Cart.Builder()
                .setCustomer(customer)
                .setComicBooks(comicBooks)
                .setCreatedDate(createdDate)
                .setUpdatedDate(updateDate)
                .build();
    }

}

