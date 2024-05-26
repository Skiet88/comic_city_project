package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.ComicBook;
import za.ac.cput.service.comicBookService.ComicBookService;

import java.util.List;

@RestController
@RequestMapping("/comic_book")
public class ComicBookController {
    @Autowired
    private ComicBookService comicBookService;

    @PostMapping("/create")
    public ComicBook create(@RequestBody ComicBook comicBook){
        System.out.println("Received ComicBook: " + comicBook);
        return comicBookService.create(comicBook);
    }

    @RequestMapping("/read/{sku}")
    public ComicBook read(@PathVariable String sku){
        return comicBookService.read(sku);
    }

    @PostMapping("/update")
    public ComicBook update(@RequestBody ComicBook comicBook){
        return comicBookService.update(comicBook);
    }


    @DeleteMapping("/delete/{sku}")
    public boolean delete(@PathVariable String sku){
        return comicBookService.delete(sku);
    }

    @GetMapping("/getAll")
    public List<ComicBook> getAll(){
        return comicBookService.getAll();
    }


}
