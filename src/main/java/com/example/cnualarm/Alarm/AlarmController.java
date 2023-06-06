package com.example.cnualarm.Alarm;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AlarmController {
    @GetMapping("test")
    public String test() {
        return "success";
    }
}
