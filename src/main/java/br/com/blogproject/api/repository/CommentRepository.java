package br.com.blogproject.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.blogproject.api.domain.post.Comment;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long>{
    
}
