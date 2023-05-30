package com.example.cnualarm.Auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    @Autowired
    AuthService service;

    @PostMapping("/auth/signup")
    public String signup(@RequestParam("id") String id, @RequestParam("pw") String pw){
        return service.signup(id, pw);
    }

    @GetMapping("/auth/login")
    public String login(@RequestParam("id") String id, @RequestParam("pw") String pw){
        return service.login(id, pw);
    }

    //test
    @GetMapping("/user/verify")
    public String verify(@RequestHeader("token") String token){
        return service.verify(token);
    }
}
