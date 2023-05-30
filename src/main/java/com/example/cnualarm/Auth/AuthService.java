package com.example.cnualarm.Auth;

import com.example.cnualarm.Entity.UserEntity;
import com.example.cnualarm.Entity.Role;
import com.example.cnualarm.repository.UserRepository;
import com.example.cnualarm.security.UserService;
import com.example.cnualarm.security.jwt.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    UserRepository repository;
    @Autowired
    UserService userService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    Jwt jwt;

    public String signup(String id, String pw) {
        if (repository.existsById(id)) {
            return "Id already exists.";
        }
        UserEntity user = new UserEntity(id, encoder.encode(pw), Role.USER);
        repository.save(user);
        return "success";
    }

    public String login(String id, String pw) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(id, pw));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String[] roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList().toArray(String[]::new);

        return jwt.sign(Jwt.Claims.from(userDetails.getUsername(),roles));
    }

    public String verify(String token){
        Jwt.Claims claims =  jwt.verify(token);
        return claims.getRoles()[0] +" "+ claims.getUsername() +" "+ claims.iat() +" "+ claims.exp();
    }
}
