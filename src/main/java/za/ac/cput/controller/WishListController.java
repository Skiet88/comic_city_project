package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.api.AddToWishListApi;
import za.ac.cput.api.RemoveFromWishListApi;
import za.ac.cput.domain.Cart;
import za.ac.cput.domain.WishList;

import za.ac.cput.service.wishListService.WishListService;

import java.util.List;

@RestController
@RequestMapping("/wishList")
public class WishListController {
private final WishListService wishListService;
private final AddToWishListApi addToWishListApi;
private final RemoveFromWishListApi removeFromWishListApi;

    public WishListController(RemoveFromWishListApi removeFromWishListApi,WishListService wishListService, AddToWishListApi addToWishListApi) {
       this.removeFromWishListApi=removeFromWishListApi;
        this.addToWishListApi=addToWishListApi;
        this.wishListService = wishListService;
    }


    @PostMapping("/create")
    public WishList create(@RequestBody WishList WishList) {
        return wishListService.create(WishList);
    }

    @GetMapping("/read/{wishListId}")
    public WishList read(@PathVariable("wishListId") Long wishListId) {
        return wishListService.read(wishListId);
    }

    @PostMapping("/update")
    public WishList update(@RequestBody WishList WishList) {
        return wishListService.update(WishList);
    }

    @DeleteMapping("delete/{wishListId}")
    public boolean delete(@PathVariable("wishListId") Long wishListId) {
        return wishListService.delete(wishListId);
    }

    @GetMapping("/getall")
    public List<WishList> getall() {
        return wishListService.getall();
    }

    @GetMapping("/quantity/{wishListId}")
    public int quantity(@PathVariable("wishListId") Long wishListId) {
        return wishListService.calculateQuantity(wishListId);
    }
    @PostMapping("/{wishListId}/addComicBook/{sku}")
    public WishList addToWishList(@PathVariable("wishListId") Long wishListId, @PathVariable("sku") Long sku){
        return addToWishListApi.addComicBookToWishList(wishListId,sku);
    }
    @PostMapping("/{wishListId}/removeComicBook/{sku}")
    public WishList removeFromWishList(@PathVariable("wishListId") Long cartId,@PathVariable("sku") Long sku){
        return removeFromWishListApi.removeBookFromWishList(cartId,sku);
    }
    @GetMapping("/getCustomerWishList/{email}")
    public WishList getCustomerWishList(@PathVariable("email") String email){
        return wishListService.getWishListWithCustomerEmail(email);
    }
}
