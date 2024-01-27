package br.com.blogproject.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.blogproject.api.domain.user.User;
import br.com.blogproject.api.repository.BlogRepository;

@RestController
@CrossOrigin(origins = "*")
public class Controller {

    @Autowired
    private BlogRepository action;

    @PostMapping("/")
    public User createUser(@RequestBody User user){
        return action.save(user);
    }
    
    @GetMapping("/")
    public Iterable<User> findAllUsers(){
        return action.findAll();
    }

    @PutMapping("/")
    public User editUser(@RequestBody User user){
        return action.save(user);
    }

    @DeleteMapping("/{id_user}")
    public void removeUser(@PathVariable Long id_user){
        action.deleteById(id_user);
    }
}
