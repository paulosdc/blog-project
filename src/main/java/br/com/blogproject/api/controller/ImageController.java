package br.com.blogproject.api.controller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import br.com.blogproject.api.domain.post.Post;

@RestController
@CrossOrigin(origins = "*")
public class ImageController {
    private static String imagesPath = "src/main/java/br/com/blogproject/api/infra/posts/";

    @GetMapping("/api/infra/posts/{imageName}")
    public ResponseEntity<byte[]> getImage(@PathVariable String imageName) {
        Path imagePath = Paths.get(imagesPath, imageName);

        try {
            Resource resource = new UrlResource(imagePath.toUri());
            if (resource.exists() && resource.isReadable()) {
                MediaType mediaType = MediaType.IMAGE_JPEG;

                byte[] imageBytes = Files.readAllBytes(imagePath);
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(mediaType);
                return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Image not found");
            }
        } catch (MalformedURLException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Malformed URL", e);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error reading image", e);
        }
    }
}
