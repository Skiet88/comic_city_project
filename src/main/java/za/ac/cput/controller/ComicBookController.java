package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.ComicBook;
import za.ac.cput.service.comicBookService.ComicBookService;

import java.util.List;
//@CrossOrigin(origins = "*" )
@RestController
@RequestMapping("/comic_book")

public class ComicBookController {
    @Autowired
    private ComicBookService comicBookService;

    @PostMapping("/create")
    public ComicBook create(@RequestBody ComicBook comicBook) {
        System.out.println("Entered Comic book");
        System.out.println(comicBook);
        ComicBook savedComicBook = comicBookService.create(comicBook);
        return savedComicBook;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/read/{sku}")
    public ResponseEntity read(@PathVariable("sku") Long sku) {
        if (sku == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        ComicBook comicBook = comicBookService.read(sku);

        if (comicBook == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(comicBook, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ComicBook update(@RequestBody ComicBook comicBook) {
        return comicBookService.update(comicBook);
    }

    @DeleteMapping("/delete/{sku}")
    public boolean delete(@PathVariable("sku") Long sku) {
        return comicBookService.delete(sku);
    }

    @GetMapping("/getAll")
    public List<ComicBook> getAll() {
        return comicBookService.getAll();
    }


}
