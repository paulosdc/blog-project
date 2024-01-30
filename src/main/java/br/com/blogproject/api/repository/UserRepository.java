package br.com.blogproject.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import br.com.blogproject.api.domain.user.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    UserDetails findByEmail(String email);
}
