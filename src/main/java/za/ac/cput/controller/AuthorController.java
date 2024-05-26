package za.ac.cput.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Author;
import za.ac.cput.service.authorService.AuthorService;

import javax.swing.*;
import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @PostMapping("/create")
    public Author create(@RequestBody  Author author){
        return authorService.create(author);
    }
   @GetMapping("/read/{authorID}")
    public Author read(@PathVariable long authorID){
        return authorService.read(authorID);
    }

    @PostMapping("/delete/{authorID}")
    public boolean delete(@PathVariable long authorID){
        return authorService.delete(authorID);
    }

    @PostMapping("/update")
    public Author update(@RequestBody Author author){
        return  authorService.update(author);
    }

    @GetMapping("/getAll")
    public List<Author> getAll(){
        return authorService.getAll();
    }


}
