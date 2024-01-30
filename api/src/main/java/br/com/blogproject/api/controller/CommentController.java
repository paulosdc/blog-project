package br.com.blogproject.api.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
public class CommentController {
    
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @PostMapping("/posts/comments")
    public Comment createComment(@RequestBody Comment comment){
        if(comment.getCreator() != null){
            Optional<User> optionalCreator = userRepository.findById(comment.getCreator().getId());
            if(optionalCreator.isPresent()){
                comment.setCreator(optionalCreator.get());
            }
        }
        if(comment.getPost() != null){
            Optional<Post> optionalPost = postRepository.findById(comment.getPost().getId());
            if(optionalPost.isPresent()){
                comment.setPost(optionalPost.get());
            }
        }
        return commentRepository.save(comment); 
    }
    
    @GetMapping("/posts/comments")
    public Iterable<Comment> findAllComments(){
        return commentRepository.findAll();
    }

    @DeleteMapping("/posts/comments/{id_comment}")
    public void removeComment(@PathVariable Long id_comment){
        commentRepository.deleteById(id_comment);
    } 

}
