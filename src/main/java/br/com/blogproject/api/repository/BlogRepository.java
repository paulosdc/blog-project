package br.com.blogproject.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import br.com.blogproject.api.domain.user.User;


@Repository
public interface BlogRepository extends CrudRepository<User, Long>{
    UserDetails findByEmail(String email);
}
