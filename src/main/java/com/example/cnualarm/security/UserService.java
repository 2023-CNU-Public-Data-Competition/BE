package com.example.cnualarm.security;

import com.example.cnualarm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUserId(username)
                .map(user ->
                        User.builder()
                                .username(user.getUserId())
                                .password(user.getPassword())
                                .authorities(new SimpleGrantedAuthority(user.getRole().toString()))
                                .build()
                )
                .orElseThrow(() -> new UsernameNotFoundException("Could not found user for " + username));
    }

}
