package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.WishList;
import za.ac.cput.service.wishListService.WishListService;

import java.util.List;

@RestController
@RequestMapping("/wishlist")
public class WishListController {
    @Autowired
    private WishListService service;


    @PostMapping("/create")
    public WishList create(@RequestBody WishList wishList){
        return service.create(wishList);
    }

    @GetMapping("/read/{wishListId}")
    public WishList read(@PathVariable long wishListId){
        return service.read(wishListId);
    }

    @PostMapping("/update")
    public WishList update(@RequestBody WishList wishList){
        return service.update(wishList);
    }

    @DeleteMapping("/delete/{wishListId}")
    public boolean delete(@PathVariable long wishListId){
        return service.delete(wishListId);
    }
    @GetMapping("/getAll")
    public List<WishList> getall(){
        return service.getall();
    }






}
