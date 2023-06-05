package com.example.cnualarm.Auth;

import com.example.cnualarm.Entity.UserEntity;
import com.example.cnualarm.Entity.Role;
import com.example.cnualarm.category.CategoryService;
import com.example.cnualarm.repository.UserRepository;
import com.example.cnualarm.security.UserService;
import com.example.cnualarm.security.jwt.Jwt;
import com.google.gson.JsonObject;
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
    CategoryService categoryService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    Jwt jwt;

    public JsonObject signup(UserInput input) throws Exception {
        if (!repository.existsById(input.id)) {
            UserEntity user = new UserEntity(input.id, encoder.encode(input.password), Role.USER);
            repository.save(user);

            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(input.id, input.password));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            String[] roles = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .toList().toArray(String[]::new);

            JsonObject result = new JsonObject();
            result.addProperty("token", jwt.sign(Jwt.Claims.from(userDetails.getUsername(),roles)));
            return result;
        }
        else throw new Exception("id already exists.");
    }

    public JsonObject login(UserInput input) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(input.id, input.password));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String[] roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList().toArray(String[]::new);

        JsonObject result = new JsonObject();
        result.addProperty("token", jwt.sign(Jwt.Claims.from(userDetails.getUsername(),roles)));
        result.add("likedCategoryList", categoryService.getLikedCategoryByUserId(input.id));
        return result;
    }

    public String verify(String token){
        Jwt.Claims claims =  jwt.verify(token);
        return claims.getRoles()[0] +" "+ claims.getUsername() +" "+ claims.iat() +" "+ claims.exp();
    }
}
