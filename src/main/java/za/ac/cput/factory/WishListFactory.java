package za.ac.cput.factory;

import za.ac.cput.domain.ComicBook;
import za.ac.cput.domain.Customer;
import za.ac.cput.domain.WishList;
import za.ac.cput.util.Helper;

import java.time.LocalDate;
import java.util.List;

public class WishListFactory {

    /**
     * Mpumzi Mbula
     * 219053324
     * 17/05/2024
     *
     */
    public static  WishList buildWishList(Long wishListId, String wishListName, Customer customer, List<ComicBook> comicBooks, LocalDate createdDate, LocalDate updatedDate){
        if(wishListId<=0 || Helper.isStringNullorEmpty(wishListName)|| Helper.isObjectNull(customer)||Helper.isListNullorEmpty(comicBooks) ||
                Helper.isAfter(createdDate, LocalDate.now()) ||
                Helper.isAfter(updatedDate, LocalDate.now())){
    return null;
        }
        return new WishList.Builder().setWishListId(wishListId)
                .setWishListName(wishListName)
                .setCustomer(customer)
                .setComicBooks(comicBooks)
                .setCreatedDate(createdDate)
                .setUpdatedDate(updatedDate)
                .build();
    }
    public static  WishList buildWishList( String wishListName, Customer customer, List<ComicBook> comicBooks, LocalDate createdDate, LocalDate updatedDate){
        if( Helper.isStringNullorEmpty(wishListName)|| Helper.isObjectNull(customer)||Helper.isListNullorEmpty(comicBooks) ||
                Helper.isAfter(createdDate, LocalDate.now()) ||
                Helper.isAfter(updatedDate, LocalDate.now())){
            return null;
        }
        return new WishList.Builder()
                .setWishListName(wishListName)
                .setCustomer(customer)
                .setComicBooks(comicBooks)
                .setCreatedDate(createdDate)
                .setUpdatedDate(updatedDate)
                .build();
    }
}
