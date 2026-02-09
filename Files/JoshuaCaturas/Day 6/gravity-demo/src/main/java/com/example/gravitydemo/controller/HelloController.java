package com.example.gravitydemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HelloController {

    @GetMapping("/")
    public String helloPage() {
        return "hello.html";
    }
}

@RestController
@RequestMapping("/api")
class HelloApiController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello World!";
    }
}
