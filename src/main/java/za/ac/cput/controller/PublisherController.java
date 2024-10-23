package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Publisher;
import za.ac.cput.service.publisherService.PublisherService;

import java.util.List;

@RestController
@RequestMapping("/Publisher")
public class PublisherController {
    @Autowired
    private PublisherService service;

    @PostMapping("/create")
    public Publisher create(@RequestBody Publisher publisher){
        return service.create(publisher);
    }

    @GetMapping("/read/{publisherid}")
    public Publisher read(@PathVariable long publisherid){
        return service.read(publisherid);
    }

    @PostMapping("/update")
    public Publisher update(@RequestBody Publisher publisher){
        return service.update(publisher);
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable long id){
        return service.delete(id);
    }

    @GetMapping("/getall")
    public List<Publisher> getall(){
        return service.getall();
    }

    @GetMapping("/findByName/{name}")
    public List<Publisher> findByName(@PathVariable("name") String name){
        return service.findByName(name);
    }

}
