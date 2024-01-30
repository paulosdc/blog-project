package br.com.blogproject.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.blogproject.api.domain.post.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{

}
