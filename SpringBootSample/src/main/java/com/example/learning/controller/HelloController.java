package com.example.learning.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping(value = "/")
    public String helloWorld() {
        return "Its serious Learn otherwise will lose job";
    }
}
