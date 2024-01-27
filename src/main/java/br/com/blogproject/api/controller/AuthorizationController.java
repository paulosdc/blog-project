package br.com.blogproject.api.controller;

import org.springframework.web.bind.annotation.RestController;

import br.com.blogproject.api.domain.user.AuthenticationDTO;
import br.com.blogproject.api.domain.user.LoginResponseDTO;
import br.com.blogproject.api.domain.user.RegisterDTO;
import br.com.blogproject.api.domain.user.User;
import br.com.blogproject.api.infra.security.TokenService;
import br.com.blogproject.api.repository.BlogRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("auth")
public class AuthorizationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private BlogRepository repository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticationDTO data){
        var usernamePassoword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassoword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterDTO data){
        if(this.repository.findByEmail(data.email()) != null){
            return ResponseEntity.badRequest().build();
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.name(), data.email(), data.username(), encryptedPassword);

        this.repository.save(newUser);

        return ResponseEntity.ok().build();
    }
    
}
