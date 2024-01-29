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

import br.com.blogproject.api.domain.post.Comment;
import br.com.blogproject.api.repository.CommentRepository;

@RestController
@CrossOrigin(origins = "*")
public class CommentController {
    
    @Autowired
    private CommentRepository commentRepository;

    @PostMapping("/posts/comments")
    public Comment createComment(@RequestBody Comment comment){
        return commentRepository.save(comment);
    }
    
    @GetMapping("/posts/comments")
    public Iterable<Comment> findAllComments(){
        return commentRepository.findAll();
    }

    @PutMapping("/posts/comments")
    public Comment editComment(@RequestBody Comment comment){
        return commentRepository.save(comment);
    }

    @DeleteMapping("/posts/comments/{id_comment}")
    public void removeComment(@PathVariable Long id_comment){
        commentRepository.deleteById(id_comment);
    }

}
