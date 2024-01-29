package br.com.blogproject.api.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.blogproject.api.domain.post.Post;
import br.com.blogproject.api.domain.user.User;
import br.com.blogproject.api.repository.PostRepository;
import br.com.blogproject.api.repository.UserRepository;

@RestController
@CrossOrigin(origins = "*")
public class PostController {
    
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;
    
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
            for(String imagePath : post.getContent().getImages()){
                Path uploadPath = Path.of(imagesPath).toAbsolutePath().normalize();
                String fileName = generateUniqueFileName("teste" + post.getContent().getText());
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

    private String generateUniqueFileName(String originalFileName) {
        // Adicionar um prefixo de timestamp para garantir exclusividade
        String timestamp = String.valueOf(System.currentTimeMillis());
        return timestamp + "_" + originalFileName + "." + "png";
    }

}
