package com.example.cnualarm.Auth;

import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    @Autowired
    AuthService service;

    // 회원가입 API
    @PostMapping("/auth/signup")
    public JsonObject signup(@RequestBody UserInput input) throws Exception {
        return service.signup(input);
    }

    // 로그인 API
    @PostMapping("/auth/login")
    public JsonObject login(@RequestBody UserInput input){
        return service.login(input);
    }

    //test
    @GetMapping("/user/verify")
    public String verify(@RequestHeader("token") String token){
        return service.verify(token);
    }
}
