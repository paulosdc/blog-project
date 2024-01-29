package br.com.blogproject.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.blogproject.api.domain.post.Content;

@Repository
public interface ContentRepository extends CrudRepository<Content, Long>{
    
}
