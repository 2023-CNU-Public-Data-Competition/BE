package com.example.cnualarm.Auth;

import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    @Autowired
    AuthService service;

    @PostMapping("/auth/signup")
    public String signup(@RequestBody SignUpInput input) throws Exception {
        return service.signup(input);
    }

    @GetMapping("/auth/login")
    public JsonObject login(@RequestParam("id") String id, @RequestParam("password") String pw){
        return service.login(id, pw);
    }

    //test
    @GetMapping("/user/verify")
    public String verify(@RequestHeader("token") String token){
        return service.verify(token);
    }
}
