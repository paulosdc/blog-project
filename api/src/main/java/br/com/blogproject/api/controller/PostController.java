package br.com.blogproject.api.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.blogproject.api.domain.post.Comment;
import br.com.blogproject.api.domain.post.Content;
import br.com.blogproject.api.domain.post.Post;
import br.com.blogproject.api.domain.user.User;
import br.com.blogproject.api.repository.CommentRepository;
import br.com.blogproject.api.repository.ContentRepository;
import br.com.blogproject.api.repository.PostRepository;
import br.com.blogproject.api.repository.UserRepository;

@RestController
@CrossOrigin(origins = "*")
public class PostController {
    
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ContentRepository contentRepository;
    
    private String imagesPath = "src/main/java/br/com/blogproject/api/infra/posts";

    @PostMapping("/posts")
    public Post createPost(@RequestBody Post post){
        if(post.getCreator() != null){
            Optional<User> optionalCreator = userRepository.findById(post.getCreator().getId());
            if(optionalCreator.isPresent()){
                post.setCreator(optionalCreator.get());
            }
        }
        if(post.getContent().getImages().size() > 0){
            List<String> images = new ArrayList<>();
            int index = 0;
            for(String imagePath : post.getContent().getImages()){
                index++;
                Path uploadPath = Path.of(imagesPath).toAbsolutePath().normalize();
                String partOfRealName = imagePath.substring(imagePath.length() - 5);
                String fileName = generateUniqueFileName("image" + index + partOfRealName.replace("/", "_"));
                Path targetPath = uploadPath.resolve(fileName);
                try {
                    byte[] imageBytes = Base64.getDecoder().decode(imagePath);
                    Files.write(targetPath, imageBytes);
                    images.add(fileName);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            post.getContent().setImages(images);
        }
        return postRepository.save(post);
    }
    
    @GetMapping("/posts")
    public Iterable<Post> findAllPosts(){
        return postRepository.findAll();
    }

    @GetMapping("/posts/{idPost}")
    public ResponseEntity<Post> findById(@PathVariable Long idPost) {
        Optional<Post> result = postRepository.findById(idPost);
        return result.map(post -> ResponseEntity.ok().body(post))
                    .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/posts")
    public Post editPost(@RequestBody Post post){
        return postRepository.save(post);
    }

    @DeleteMapping("/posts/{id_post}")
    public void removePost(@PathVariable Long id_post){
        Iterable<Comment> comments = commentRepository.findAll();
        for(Comment comment : comments){
            if(comment.getPost().getId() == id_post){
                commentRepository.delete(comment);
            }
        }
        Iterable<Content> contents = contentRepository.findAll();
        Optional<Post> optionalPost = postRepository.findById(id_post);
        if(optionalPost.isPresent()){
            for(Content content : contents){
                if(content.getId() == optionalPost.get().getContent().getId()){
                    contentRepository.delete(content);
                }
            }
        }
        
        postRepository.deleteById(id_post);
    } 

    private String generateUniqueFileName(String originalFileName) {
        String timestamp = String.valueOf(System.currentTimeMillis());
        return timestamp + "_" + originalFileName + ".png";
    }

}
