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

import br.com.blogproject.api.domain.post.Content;
import br.com.blogproject.api.repository.ContentRepository;

@RestController
@CrossOrigin(origins = "*")
public class ContentController {
    
    @Autowired
    private ContentRepository contentRepository;

    @PostMapping("/posts/content")
    public Content createContent(@RequestBody Content content){
        return contentRepository.save(content);
    }
    
    @GetMapping("/posts/content")
    public Iterable<Content> findAllContents(){
        return contentRepository.findAll();
    }

    @PutMapping("/posts/content")
    public Content editContent(@RequestBody Content content){
        return contentRepository.save(content);
    }

    @DeleteMapping("/posts/content/{id_content}")
    public void removeContent(@PathVariable Long id_content){
        contentRepository.deleteById(id_content);
    }
}
