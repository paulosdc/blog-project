package br.com.blogproject.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.blogproject.api.domain.post.Comment;
import br.com.blogproject.api.domain.post.Post;
import br.com.blogproject.api.domain.user.User;
import br.com.blogproject.api.repository.CommentRepository;
import br.com.blogproject.api.repository.PostRepository;
import br.com.blogproject.api.repository.UserRepository;

@RestController
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;


    @PostMapping("/")
    public User createUser(@RequestBody User user){
        return userRepository.save(user);
    }
    
    @GetMapping("/{email}")
    public UserDetails findAllUsers(@PathVariable String email){
        return userRepository.findByEmail(email);
    }

    @PutMapping("/")
    public User editUser(@RequestBody User user){
        return userRepository.save(user);
    }

    @DeleteMapping("/{id_user}")
    public void removeUser(@PathVariable Long id_user){
        Iterable<Comment> comments = commentRepository.findAll();
        Iterable<Post> posts = postRepository.findAll();
        for(Post post : posts){
            if(post.getCreator().getId() == id_user){
                for(Comment comment : comments){
                    if(comment.getCreator().getId() == id_user || comment.getPost().getCreator().getId() == id_user){
                        commentRepository.delete(comment);
                    }
                }
                postRepository.delete(post);
            }
        }
        userRepository.deleteById(id_user);
    }
}
